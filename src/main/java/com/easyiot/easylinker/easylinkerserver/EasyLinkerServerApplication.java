package com.easyiot.easylinker.easylinkerserver;

import com.easyiot.easylinker.easylinkerserver.vertxmqtt.MqttServerRunner;
import com.easyiot.easylinker.easylinkerserver.vertxmqtt.VertXMqttServer;
import io.vertx.core.Verticle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class EasyLinkerServerApplication {
    @Bean
    public Verticle runMqttServer(){
        return  MqttServerRunner. run(new VertXMqttServer());
    }

    public static void main(String[] args) {
        //MqttServerRunner. run(new VertXMqttServer());
        SpringApplication.run(EasyLinkerServerApplication.class, args);
    }
}
