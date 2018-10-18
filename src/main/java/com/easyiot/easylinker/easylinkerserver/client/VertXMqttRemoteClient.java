package com.easyiot.easylinker.easylinkerserver.client;

import org.springframework.data.annotation.Id;

import java.util.Arrays;

/**
 * 持久户化的设备客户端
 * 这个类用来关联状态
 */

public class VertXMqttRemoteClient extends BaseEntity {

    private String username;
    private String password;
    private String clientId;
    private String topics[];
    private Boolean onLine=false;

    public Boolean getOnLine() {
        return onLine;
    }

    public void setOnLine(Boolean onLine) {
        this.onLine = onLine;
    }

    public String[] getTopics() {
        return topics;
    }

    public void setTopics(String[] topics) {
        this.topics = topics;
    }

    public String getUsername() {
        return username;
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

    @Override
    public String toString() {
        return this.getId() + "|" +
                this.getClientId() + "|"
                + this.getUsername() + "|"
                + this.getPassword() + "|"
                + Arrays.deepToString(this.getTopics())

                + "|";
    }
}
