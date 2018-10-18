package com.easyiot.easylinker.easylinkerserver.client;

import org.springframework.data.annotation.Id;

/**
 * 持久户化的设备客户端
 * 这个类用来关联状态
 */

public class VertXMqttRemoteClient{
    @Id

    private Long id;

    private String username;
    private String password;
    private String clientId;

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
