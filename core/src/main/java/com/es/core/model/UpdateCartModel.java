package com.es.core.model;

import java.util.ArrayList;
import java.util.List;

public class UpdateCartModel {

    private List<Long> phonesId;

    private List<Integer> quantity;

    public UpdateCartModel() {
        phonesId = new ArrayList<>();
        quantity = new ArrayList<>();
    }

    public List<Long> getPhonesId() {
        return phonesId;
    }

    public void setPhonesId(List<Long> phonesId) {
        this.phonesId = phonesId;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }
}

