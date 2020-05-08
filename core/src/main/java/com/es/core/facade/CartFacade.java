package com.es.core.facade;

import com.es.core.converter.FromCartToCartDataConverter;
import com.es.core.data.CartData;
import com.es.core.exception.PhoneNotFoundException;
import com.es.core.form.AddToCartForm;
import com.es.core.form.QuickOrderForm;
import com.es.core.form.UpdateCartForm;
import com.es.core.model.CartModel;
import com.es.core.model.QuickOrderEntity;
import com.es.core.service.CartService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartFacade {

    @Resource
    private CartService cartService;

    @Resource
    private FromCartToCartDataConverter fromCartToCartDataConverter;

    public void addToCart(AddToCartForm addToCartForm) throws PhoneNotFoundException {
        cartService.addPhone(Long.valueOf(addToCartForm.getPhoneId()), Integer.valueOf(addToCartForm.getQuantity()));
    }

    public void addToCart(QuickOrderEntity quickOrderEntity) throws PhoneNotFoundException {
        String model = quickOrderEntity.getModel();
        Integer quantity = Integer.valueOf(quickOrderEntity.getQuantity());
        cartService.addPhone(model, quantity);
    }

    public void deleteFromCart(Long phoneId) throws PhoneNotFoundException {
        cartService.remove(phoneId);
    }

    public void updateCart(UpdateCartForm updateCartForm) {
        cartService.update(updateCartForm.getUpdatePhonesIds(), updateCartForm.getUpdatePhonesQuantities());
    }

    public CartData getCartData() {
        CartModel cart = cartService.getCart();
        CartData cartData = fromCartToCartDataConverter.convert(cart);
        return cartData;
    }
}
