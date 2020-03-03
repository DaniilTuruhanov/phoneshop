package com.es.core.populator.impl;

import com.es.core.data.CartData;
import com.es.core.model.Cart;
import com.es.core.populator.inter.Populator;
import org.springframework.stereotype.Component;

@Component
public class FromCartToCartDataPopulator implements Populator<Cart, CartData> {

    @Override
    public void populate(Cart cart, CartData cartData) {
        cartData.setTotalCost(cart.getTotalCost());
    }
}
