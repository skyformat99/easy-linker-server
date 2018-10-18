package com.easyiot.easylinker.easylinkerserver;

import com.easyiot.easylinker.easylinkerserver.client.VertxMqttRemoteClient;
import com.easyiot.easylinker.easylinkerserver.client.VertxMqttRemoteClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyLinkerServerApplicationTests {

    @Autowired
    VertxMqttRemoteClientService vertxMqttRemoteClientService;
    @Test
    public void contextLoads() {
        VertxMqttRemoteClient vertxMqttRemoteClient=new VertxMqttRemoteClient();
        vertxMqttRemoteClient.setId(System.currentTimeMillis());
        vertxMqttRemoteClient.setUsername("username");
        vertxMqttRemoteClient.setPassword("password");
        vertxMqttRemoteClientService.save(vertxMqttRemoteClient);
    }

}
