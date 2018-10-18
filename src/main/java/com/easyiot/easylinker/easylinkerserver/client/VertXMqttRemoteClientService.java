package com.easyiot.easylinker.easylinkerserver.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VertXMqttRemoteClientService implements BaseServive<VertXMqttRemoteClient>{
    @Autowired
    VertXMqttRemoteClientRepository vertxMqttRemoteClientRepository;


    @Override
    public void save(VertXMqttRemoteClient vertXMqttRemoteClient) {

        vertxMqttRemoteClientRepository.save(vertXMqttRemoteClient);
    }

    @Override
    public void delete(VertXMqttRemoteClient vertXMqttRemoteClient) {
        vertxMqttRemoteClientRepository.delete(vertXMqttRemoteClient);

    }
}
