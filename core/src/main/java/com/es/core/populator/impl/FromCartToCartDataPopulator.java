package com.es.core.populator.impl;

import com.es.core.data.CartData;
import com.es.core.data.PhoneData;
import com.es.core.model.CartModel;
import com.es.core.model.CartEntity;
import com.es.core.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class FromCartToCartDataPopulator implements Populator<CartModel, CartData> {

    @Override
    public void populate(CartModel cart, CartData cartData) {
        for (CartEntity cartEntity : cart.getCartEntityList()) {
            PhoneData phoneData = new PhoneData();
            phoneData.setBrand(cartEntity.getPhone().getBrand());
            phoneData.setModel(cartEntity.getPhone().getModel());
            phoneData.setDisplaySizeInches(cartEntity.getPhone().getDisplaySizeInches());
            phoneData.setColors(cartEntity.getPhone().getColors());
            phoneData.setPrice(cartEntity.getPhone().getPrice());
            phoneData.setId(cartEntity.getPhone().getId());
            phoneData.setQuantity(cartEntity.getQuantity());
            cartData.getCartEntityList().add(phoneData);
        }
        cartData.setTotalQuantity(cart.getTotalQuantity());
        cartData.setTotalCost(cart.getTotalCost());
    }
}
