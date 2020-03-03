package com.es.core.converter;

import com.es.core.data.CartData;
import com.es.core.model.Cart;
import com.es.core.populator.inter.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class FromCartToCartDataConverter implements Converter<Cart, CartData> {

    private List<Populator> populators;

    @Override
    public CartData convert(Cart cart) {
        CartData cartData = new CartData();
        for (Populator populator : populators) {
            populator.populate(cart, cartData);
        }
        return cartData;
    }

    public List<Populator> getPopulators() {
        return populators;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}