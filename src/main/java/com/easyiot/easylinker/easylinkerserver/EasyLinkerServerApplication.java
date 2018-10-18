package com.easyiot.easylinker.easylinkerserver;

import com.easyiot.easylinker.easylinkerserver.vertxmqtt.MqttServerRunner;
import com.easyiot.easylinker.easylinkerserver.vertxmqtt.VertxMqttServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EasyLinkerServerApplication {


    public static void main(String[] args) {
        MqttServerRunner. run(new VertxMqttServer());
        SpringApplication.run(EasyLinkerServerApplication.class, args);
    }
}
