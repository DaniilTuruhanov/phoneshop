package com.es.core.facade;

import com.es.core.converter.FromCartToCartDataConverter;
import com.es.core.data.CartData;
import com.es.core.model.Cart;
import com.es.core.service.inter.CartService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GetCartDataFacade {

    @Resource
    private FromCartToCartDataConverter fromCartToCartDataConverter;

    @Resource
    private CartService cartService;

    public CartData getCartData(){
        Cart cart = cartService.getCart();
        CartData cartData = fromCartToCartDataConverter.convert(cart);
        return cartData;
    }
}
