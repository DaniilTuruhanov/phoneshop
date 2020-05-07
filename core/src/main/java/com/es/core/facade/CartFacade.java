package com.es.core.facade;

import com.es.core.converter.FromCartToCartDataConverter;
import com.es.core.data.CartData;
import com.es.core.exception.PhoneNotFoundException;
import com.es.core.form.AddToCartForm;
import com.es.core.form.QuickOrderForm;
import com.es.core.form.UpdateCartForm;
import com.es.core.model.CartModel;
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

    public int addToCart(QuickOrderForm quickOrderForm) throws PhoneNotFoundException {
        List<Integer> quantity = new ArrayList<>();
        List<String> model = new ArrayList<>();
        for (int i = 0; i < quickOrderForm.getPhoneQuantity().size(); i++) {
            if (!quickOrderForm.getPhoneQuantity().get(i).equals("")) {
                model.add(quickOrderForm.getPhoneModel().get(i));
                quantity.add(Integer.valueOf(quickOrderForm.getPhoneQuantity().get(i)));
            }
        }
        cartService.addPhone(model, quantity);
        return model.size();
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
