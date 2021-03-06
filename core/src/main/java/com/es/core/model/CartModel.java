package com.es.core.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartModel {
    private BigDecimal totalCost;

    private Integer totalQuantity;

    private List<CartEntity> cartEntityList;

    public CartModel() {
        totalCost = BigDecimal.valueOf(0);
        cartEntityList = new ArrayList<>();
        totalQuantity = 0;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public List<CartEntity> getCartEntityList() {
        return cartEntityList;
    }

    public void setCartEntityList(List<CartEntity> cartEntityList) {
        this.cartEntityList = cartEntityList;
    }
}

