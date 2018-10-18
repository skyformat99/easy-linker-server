package com.easyiot.easylinker.easylinkerserver.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VertXMqttRemoteClientService implements BaseServive<VertXMqttRemoteClient> {
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

    @Override
    public Page<VertXMqttRemoteClient> getAll(Pageable pageable) {
        return vertxMqttRemoteClientRepository.findAll(pageable);
    }

    /**
     * 鉴权的时候查询
     * @param username
     * @param password
     * @return
     */
    public VertXMqttRemoteClient findTopByUsernameAndPassword(String username, String password) {
        return vertxMqttRemoteClientRepository.findTopByUsernameAndPassword(username, password);
    }

    /**
     * 根据ClientId鉴权
     * @param clientId
     * @return
     */
    public VertXMqttRemoteClient findTopByClientId(String clientId) {
        return vertxMqttRemoteClientRepository.findTopByClientId(clientId);
    }
}

