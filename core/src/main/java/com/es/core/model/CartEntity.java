package com.es.core.model;

import java.util.Objects;

public class CartEntity {
    private Phone phone;

    private Integer quantity;

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartEntity cartEntity = (CartEntity) o;
        return Objects.equals(phone, cartEntity.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }
}
