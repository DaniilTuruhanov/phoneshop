package com.es.core.exception;

public class OrderNotFoundException extends Exception {
    private String id;

    public OrderNotFoundException(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
