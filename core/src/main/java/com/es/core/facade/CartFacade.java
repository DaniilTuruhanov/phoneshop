package com.es.core.facade;

import com.es.core.converter.FromAddToCartFormToModelConverter;
import com.es.core.exception.PhoneNotFoundException;
import com.es.core.form.AddToCartForm;
import com.es.core.model.AddToCartModel;
import com.es.core.service.inter.CartService;
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
}
