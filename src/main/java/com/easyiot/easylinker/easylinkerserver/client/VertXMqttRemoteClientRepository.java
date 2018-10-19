package com.easyiot.easylinker.easylinkerserver.client;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VertXMqttRemoteClientRepository extends MongoRepository<VertXMqttRemoteClient,Long> {
    VertXMqttRemoteClient findTopByUsernameAndPassword(String username,String password);
    VertXMqttRemoteClient findTopByClientId(String clientId);
    List <VertXMqttRemoteClient> findAllByOnLine(Boolean online);
}
