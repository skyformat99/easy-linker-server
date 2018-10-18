package com.easyiot.easylinker.easylinkerserver.client;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VertxMqttRemoteClientRepository extends MongoRepository<VertxMqttRemoteClient,Long> {
}
