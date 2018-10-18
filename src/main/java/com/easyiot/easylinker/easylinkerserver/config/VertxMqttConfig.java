package com.easyiot.easylinker.easylinkerserver.config;

import com.easyiot.easylinker.easylinkerserver.vertxmqtt.MqttServerRunner;
import com.easyiot.easylinker.easylinkerserver.vertxmqtt.VertxMqttServer;
import io.vertx.core.Verticle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VertxMqttConfig {
    //@Bean
    public Verticle runMqttServer(){
      return  MqttServerRunner. run(new VertxMqttServer());
    }
}
