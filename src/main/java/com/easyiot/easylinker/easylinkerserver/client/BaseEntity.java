package com.easyiot.easylinker.easylinkerserver.client;

import org.springframework.data.annotation.Id;

public class BaseEntity {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
