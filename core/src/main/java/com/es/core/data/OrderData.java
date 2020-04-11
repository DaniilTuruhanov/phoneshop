package com.es.core.data;

import com.es.core.model.Phone;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderData {

    private List<PhoneData> orderEntityList = new ArrayList<>();

    private BigDecimal subtotalCost;

    private BigDecimal totalCost;

    private BigDecimal deliveryCost;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PhoneData> getOrderEntityList() {
        return orderEntityList;
    }

    public void setOrderEntityList(List<PhoneData> orderEntityList) {
        this.orderEntityList = orderEntityList;
    }

    public BigDecimal getSubtotalCost() {
        return subtotalCost;
    }

    public void setSubtotalCost(BigDecimal subtotalCost) {
        this.subtotalCost = subtotalCost;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }
}
