package com.es.core.facade;

import com.es.core.converter.FromAddToCartFormToModelConverter;
import com.es.core.converter.FromCartToCartDataConverter;
import com.es.core.converter.FromUpdateCartFormToModelConverter;
import com.es.core.data.CartData;
import com.es.core.exception.PhoneNotFoundException;
import com.es.core.form.AddToCartForm;
import com.es.core.form.UpdateCartForm;
import com.es.core.model.AddToCartModel;
import com.es.core.model.CartModel;
import com.es.core.model.UpdateCartModel;
import com.es.core.service.CartService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CartFacade {

    @Resource
    private CartService cartService;

    @Resource
    private FromUpdateCartFormToModelConverter fromUpdateCartFormToModelConverter;

    @Resource
    private FromAddToCartFormToModelConverter fromAddToCartFormToModelConverter;

    @Resource
    private FromCartToCartDataConverter fromCartToCartDataConverter;

    public void addToCart(AddToCartForm addToCartForm) throws PhoneNotFoundException {
        AddToCartModel addToCartModel = fromAddToCartFormToModelConverter.convert(addToCartForm);
        cartService.addPhone(addToCartModel);
    }

    public void deleteFromCart(Long phoneId) throws PhoneNotFoundException {
        cartService.remove(phoneId);
    }

    public void updateCart(UpdateCartForm updateCartForm) {
        UpdateCartModel updateCartModel = fromUpdateCartFormToModelConverter.convert(updateCartForm);
        cartService.update(updateCartModel);
    }

    public CartData getCartData() {
        CartModel cart = cartService.getCart();
        CartData cartData = fromCartToCartDataConverter.convert(cart);
        return cartData;
    }
}
