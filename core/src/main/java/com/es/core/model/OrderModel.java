package com.es.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderModel {

    private BigDecimal subtotalCost;

    private BigDecimal totalCost;

    private BigDecimal deliveryCost;

    private List<CartEntity> cartEntityList = new ArrayList<>();

    private String id;

    private Integer number;

    private UserModel userModel;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public List<CartEntity> getCartEntityList() {
        return cartEntityList;
    }

    public void setCartEntityList(List<CartEntity> cartEntityList) {
        this.cartEntityList = cartEntityList;
    }

    public BigDecimal getSubtotalCost() {
        return subtotalCost;
    }

    public void setSubtotalCost(BigDecimal subtotalCost) {
        this.subtotalCost = subtotalCost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderModel order = (OrderModel) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
