package com.es.core.form;

import java.util.List;

public class PlaceOrderForm {
    private List<Long> placeOrderId;

    private List<Integer> placeOrderQuantity;

    public List<Long> getPlaceOrderId() {
        return placeOrderId;
    }

    public void setPlaceOrderId(List<Long> placeOrderId) {
        this.placeOrderId = placeOrderId;
    }

    public List<Integer> getPlaceOrderQuantity() {
        return placeOrderQuantity;
    }

    public void setPlaceOrderQuantity(List<Integer> placeOrderQuantity) {
        this.placeOrderQuantity = placeOrderQuantity;
    }
}
