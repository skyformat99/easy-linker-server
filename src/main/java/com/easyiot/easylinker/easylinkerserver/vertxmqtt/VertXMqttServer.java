package com.easyiot.easylinker.easylinkerserver.vertxmqtt;

import com.easyiot.easylinker.easylinkerserver.client.VertXMqttRemoteClient;
import com.easyiot.easylinker.easylinkerserver.client.VertXMqttRemoteClientService;
import com.easyiot.easylinker.easylinkerserver.config.VertXMqttConfig;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.dropwizard.DropwizardMetricsOptions;
import io.vertx.ext.dropwizard.MetricsService;
import io.vertx.mqtt.MqttServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Vertx 实现的mqtt服务器
 * 连接步骤
 * 1 鉴权
 * 2 添加ACL
 * 3 判断是否连接进来
 *  增加两个选项：通过Id 或者通过Username鉴权
 */
@Component
public class VertXMqttServer extends AbstractVerticle {
    @Autowired
    VertXMqttRemoteClientService vertXMqttRemoteClientService;
    @Autowired
    VertXMqttConfig vertXMqttConfig;


    @Override
    public void start() {
        MqttServer mqttServer = MqttServer.create(vertx);
        mqttServer
                .endpointHandler(endpoint -> {

                    // shows main connect info
                    System.out.println("有客户端连接 [" + endpoint.auth().toJson() + "] clientId = " + endpoint.clientIdentifier());
//
//
//                    if (endpoint.will() != null) {
//                        System.out.println("[will flag = " + endpoint.will().isWillFlag() + " topic = " + endpoint.will().willTopic() + " msg = " + endpoint.will().willMessage() +
//                                " QoS = " + endpoint.will().willQos() + " isRetain = " + endpoint.will().isWillRetain() + "]");
//                    }
//
//                    System.out.println("[keep alive timeout = " + endpoint.keepAliveTimeSeconds() + "]");

                    //1 鉴权 ,初步采用MongoDb进行查库
                    //首先实现通过username

                    if (endpoint.auth() != null) {
                        String username = endpoint.auth().userName();
                        String password = endpoint.auth().password();
                        String clientId = endpoint.clientIdentifier();
                        String auth = vertXMqttConfig.getAuth();
                        //默认开启用户名认证¬
                        if (auth == null) auth = "username";
                        switch (auth) {
                            //开启用户名密码认证
                            case "username":
                                //如果没有账户密码直接拒绝
                                if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
                                    System.out.println("客户端连接失败！用户名或者密码空！");
                                    endpoint.reject(MqttConnectReturnCode.CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD);
                                } else {
                                    VertXMqttRemoteClient vertXMqttRemoteClient = vertXMqttRemoteClientService.findTopByUsernameAndPassword(username, password);

                                    if (vertXMqttRemoteClient == null) {
                                        System.out.println("客户端username在数据库不存在！连接失败！");
                                        vertXMqttRemoteClient.setOnLine(false);
                                        endpoint.reject(MqttConnectReturnCode.CONNECTION_REFUSED_BAD_USER_NAME_OR_PASSWORD);

                                    } else {

                                        // accept connection from the remote client
                                        endpoint.accept(true);
                                        vertXMqttRemoteClient.setOnLine(true);
                                        vertXMqttRemoteClientService.save(vertXMqttRemoteClient);
                                        System.out.println("客户端鉴权成功！连接成功！");
                                    }
                                }
                                break;
                            //开启客户端ID认证
                            case "clientId":
                                //如果没有账户密码直接拒绝
                                if (StringUtils.isEmpty(clientId)) {
                                    System.out.println("clientId空");
                                    endpoint.reject(MqttConnectReturnCode.CONNECTION_REFUSED_NOT_AUTHORIZED);
                                } else {
                                    VertXMqttRemoteClient vertXMqttRemoteClient = vertXMqttRemoteClientService.findTopByClientId(clientId);

                                    if (vertXMqttRemoteClient == null) {
                                        System.out.println("客户端不存在");
                                        vertXMqttRemoteClient.setOnLine(false);
                                        endpoint.reject(MqttConnectReturnCode.CONNECTION_REFUSED_NOT_AUTHORIZED);

                                    } else {
                                        System.out.println("客户端存在");
                                        endpoint.accept(true);
                                        vertXMqttRemoteClient.setOnLine(true);
                                        vertXMqttRemoteClientService.save(vertXMqttRemoteClient);
                                        System.out.println("客户端:" + vertXMqttRemoteClient.getClientId() + " 连接成功!");

                                    }
                                }
                                break;
                            default:
                                break;
                        }


                    } else {
                        System.out.println("开启匿名连接");
                    }

//                    //handling requests for subscriptions
//                    endpoint.subscribeHandler(subscribe -> {
//
//                        List<MqttQoS> grantedQosLevels = new ArrayList<>();
//                        for (MqttTopicSubscription s : subscribe.topicSubscriptions()) {
//                            System.out.println("Subscription for " + s.topicName() + " with QoS " + s.qualityOfService());
//                            grantedQosLevels.add(s.qualityOfService());
//                        }
//                        // ack the subscriptions request
//                        endpoint.subscribeAcknowledge(subscribe.messageId(), grantedQosLevels);
//
//                        // just as example, publish a message on the first topic with requested QoS
//                        endpoint.publish(subscribe.topicSubscriptions().get(0).topicName(),
//                                Buffer.buffer("Hello from the Vert.x MQTT server"),
//                                subscribe.topicSubscriptions().get(0).qualityOfService(),
//                                false,
//                                false);
//
//                        // specifing handlers for handling QoS 1 and 2
//                        endpoint.publishAcknowledgeHandler(messageId -> {
//
//                            System.out.println("Received ack for message = " + messageId);
//
//                        }).publishReceivedHandler(messageId -> {
//
//                            endpoint.publishRelease(messageId);
//
//                        }).publishCompletionHandler(messageId -> {
//
//                            System.out.println("Received ack for message = " + messageId);
//                        });
//                    });
//
//                    // handling requests for unsubscriptions
//                    endpoint.unsubscribeHandler(unsubscribe -> {
//
//                        for (String t : unsubscribe.topics()) {
//                            System.out.println("Unsubscription for " + t);
//                        }
//                        // ack the subscriptions request
//                        endpoint.unsubscribeAcknowledge(unsubscribe.messageId());
//                    });
//
//                    // handling ping from client
//                    endpoint.pingHandler(v -> {
//
//                        System.out.println("Ping received from client");
//                    });
//
//                    // handling disconnect message
                    /**
                     * 这个响应是客户端主动断开产生的
                     */
                    endpoint.disconnectHandler(v -> {
                        System.out.println("Received disconnect from client");
                    });
                    /**
                     * 这个响应是客户端异常断开，比如突然断电
                     */

                    // handling closing connection
                    endpoint.closeHandler(v -> {
                        System.out.println("Connection closed" + endpoint.auth().toJson() + endpoint.clientIdentifier());
                    });
//
//                    // handling incoming published messages
//                    endpoint.publishHandler(message -> {
//
//                        System.out.println("Just received message on [" + message.topicName() + "] payload [" + message.payload() + "] with QoS [" + message.qosLevel() + "]");
//
//                        if (message.qosLevel() == MqttQoS.AT_LEAST_ONCE) {
//                            endpoint.publishAcknowledge(message.messageId());
//                        } else if (message.qosLevel() == MqttQoS.EXACTLY_ONCE) {
//                            endpoint.publishReceived(message.messageId());
//                        }
//
//                    }).publishReleaseHandler(messageId -> {
//                        endpoint.publishComplete(messageId);
//                    });
                })
                .listen(1883, "0.0.0.0", ar -> {

                    if (ar.succeeded()) {
                        System.out.println("MQTT server is listening on port " + mqttServer.actualPort());
                    } else {
                        System.err.println("Error on starting the server" + ar.cause().getMessage());
                    }
                });

    }
}
