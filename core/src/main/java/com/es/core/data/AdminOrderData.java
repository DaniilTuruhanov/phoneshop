package com.es.core.data;

import com.es.core.model.Status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AdminOrderData {
    private List<PhoneData> orderEntityList = new ArrayList<>();

    private BigDecimal subtotalCost;

    private BigDecimal totalCost;

    private BigDecimal deliveryCost;

    private int number;

    private String firstName;

    private String lastName;

    private String address;

    private String phone;

    private String description;

    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
