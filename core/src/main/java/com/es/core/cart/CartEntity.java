package com.es.core.cart;

import com.es.core.phone.Phone;

import java.util.Objects;

public class CartEntity {
    private Phone phone;

    private Integer reserved;

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Integer getReserved() {
        return reserved;
    }

    public void setReserved(Integer reserved) {
        this.reserved = reserved;
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
