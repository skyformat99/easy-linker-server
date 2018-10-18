package com.easyiot.easylinker.easylinkerserver;

import com.easyiot.easylinker.easylinkerserver.client.VertXMqttRemoteClient;
import com.easyiot.easylinker.easylinkerserver.client.VertXMqttRemoteClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyLinkerServerApplicationTests {

    @Autowired
    VertXMqttRemoteClientService vertXMqttRemoteClientService;
    @Test
    public void contextLoads() {
        VertXMqttRemoteClient vertxMqttRemoteClient=new VertXMqttRemoteClient();
        vertxMqttRemoteClient.setId(System.currentTimeMillis());
        vertxMqttRemoteClient.setUsername("username");
        vertxMqttRemoteClient.setPassword("password");
        vertXMqttRemoteClientService.save(vertxMqttRemoteClient);
    }

}
