package com.es.core.models;

import org.springframework.stereotype.Component;

@Component
public class AddToCartModel {
    private String quantity;

    private String phoneId;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }
}
