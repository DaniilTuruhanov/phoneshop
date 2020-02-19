package com.es.core.cart;

public class AddToCartPhone {
    private Cart cart;
    private String quantity;
    private Long phoneId;

    public AddToCartPhone(Cart cart, String quantity, Long phoneId) {
        this.cart = cart;
        this.quantity = quantity;
        this.phoneId = phoneId;
    }

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
