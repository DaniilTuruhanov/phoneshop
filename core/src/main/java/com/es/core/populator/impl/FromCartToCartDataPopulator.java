package com.es.core.populator.impl;

import com.es.core.data.CartData;
import com.es.core.data.CartEntityData;
import com.es.core.model.Cart;
import com.es.core.model.CartEntity;
import com.es.core.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class FromCartToCartDataPopulator implements Populator<Cart, CartData> {

    @Override
    public void populate(Cart cart, CartData cartData) {
        for (CartEntity cartEntity : cart.getCartEntityList()) {
            CartEntityData cartEntityData= new CartEntityData();
            cartEntityData.setBrand(cartEntity.getPhone().getBrand());
            cartEntityData.setModel(cartEntity.getPhone().getModel());
            cartEntityData.setDisplaySizeInches(cartEntity.getPhone().getDisplaySizeInches());
            cartEntityData.setColors(cartEntity.getPhone().getColors());
            cartEntityData.setPrice(cartEntity.getPhone().getPrice());
            cartEntityData.setId(cartEntity.getPhone().getId());
            cartEntityData.setQuantity(cartEntity.getQuantity());
            cartData.getCartEntityList().add(cartEntityData);
        }
        cartData.setTotalQuantity(cart.getTotalQuantity());
        cartData.setTotalCost(cart.getTotalCost());
    }
}
