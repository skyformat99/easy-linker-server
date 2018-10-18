package com.easyiot.easylinker.easylinkerserver;

import com.easyiot.easylinker.easylinkerserver.client.VertXMqttRemoteClient;
import com.easyiot.easylinker.easylinkerserver.client.VertXMqttRemoteClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyLinkerServerApplicationTests {

    @Autowired
    VertXMqttRemoteClientService vertXMqttRemoteClientService;

    @Test
    public void contextLoads() {
        VertXMqttRemoteClient vertxMqttRemoteClient = new VertXMqttRemoteClient();
        vertxMqttRemoteClient.setId(System.currentTimeMillis());
        vertxMqttRemoteClient.setClientId("100" + System.currentTimeMillis());
        vertxMqttRemoteClient.setUsername("username");
        vertxMqttRemoteClient.setPassword("password");
        vertxMqttRemoteClient.setTopics(new String[]{"/1", "/2", "/3"});
        vertXMqttRemoteClientService.save(vertxMqttRemoteClient);
        Page<VertXMqttRemoteClient> page = vertXMqttRemoteClientService.getAll(PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "_id")));
        for (VertXMqttRemoteClient client : page.getContent()) {
            System.out.println(client.toString());
        }


    }

}
