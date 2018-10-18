package com.easyiot.easylinker.easylinkerserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.Valid;

@Configuration
public class VertXMqttConfig {
    //默认端口1883
    @Value("${vertx.mqtt.server.port}")
    private int port = 1883;
    //默认关闭匿名连接模式
    @Value("${vertx.mqtt.server.anonymous}")
    private Boolean anonymous = false;
    //默认是username认证
    //支持username clientId两种形式的认证
    @Value("${vertx.mqtt.server.auth}")
    private String auth = "username";

    public int getPort() {
        return port;
    }

    public VertXMqttConfig() {
        System.out.println("Mqtt port:" + port);
        System.out.println("Mqtt anonymous:" + anonymous);
        System.out.println("Mqtt auth:" + auth);

    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

}
