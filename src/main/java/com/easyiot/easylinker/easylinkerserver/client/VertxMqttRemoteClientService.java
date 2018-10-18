package com.easyiot.easylinker.easylinkerserver.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VertxMqttRemoteClientService implements BaseServive<VertxMqttRemoteClient>{
    @Autowired
    VertxMqttRemoteClientRepository vertxMqttRemoteClientRepository;

    @Override
    public void save(VertxMqttRemoteClient vertxMqttRemoteClient) {
        vertxMqttRemoteClientRepository.save(vertxMqttRemoteClient);

    }

    @Override
    public void delete(VertxMqttRemoteClient vertxMqttRemoteClient) {
        vertxMqttRemoteClientRepository.delete(vertxMqttRemoteClient);

    }
}
