package com.easyiot.easylinker.easylinkerserver.client;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VertXMqttRemoteClientRepository extends MongoRepository<VertXMqttRemoteClient,Long> {
    VertXMqttRemoteClient findTopByUsernameAndPassword(String username,String password);
    VertXMqttRemoteClient findTopByClientId(String clientId);
}
