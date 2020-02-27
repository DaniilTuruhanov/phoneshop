package com.es.core.facades;

import com.es.core.converters.FromAddToCartFormToModelConverter;
import com.es.core.exceptions.PhoneNotFoundException;
import com.es.core.forms.AddToCartForm;
import com.es.core.models.AddToCartModel;
import com.es.core.models.Cart;
import com.es.core.services.interfaces.CartService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CartFacade {

    @Resource
    private CartService cartService;

    @Resource
    private FromAddToCartFormToModelConverter fromAddToCartFormToModelConverter;

    public void addToCart(AddToCartForm addToCartForm) throws PhoneNotFoundException {
        AddToCartModel addToCartModel = fromAddToCartFormToModelConverter.convert(addToCartForm);
        cartService.addPhone(addToCartModel);
    }

    public Cart getCart() {
        return cartService.getCart();
    }
}
