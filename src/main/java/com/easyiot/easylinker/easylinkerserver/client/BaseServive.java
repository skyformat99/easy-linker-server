package com.easyiot.easylinker.easylinkerserver.client;

public interface BaseServive<T> {
    void save(T t);
    void delete(T t);
}
