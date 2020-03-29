package com.es.core.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartData {

    private List<PhoneData> cartEntityList = new ArrayList<>();

    private int totalQuantity;

    private BigDecimal totalCost;

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public List<PhoneData> getCartEntityList() {
        return cartEntityList;
    }

    public void setCartEntityList(List<PhoneData> cartEntityList) {
        this.cartEntityList = cartEntityList;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
