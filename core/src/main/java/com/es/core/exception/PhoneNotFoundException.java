package com.es.core.exception;

public class PhoneNotFoundException extends Exception {
    private String id;

    public PhoneNotFoundException(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
