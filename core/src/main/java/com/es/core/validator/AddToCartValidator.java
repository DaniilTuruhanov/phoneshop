package com.es.core.validator;

import com.es.core.form.AddToCartForm;
import com.es.core.model.CartEntity;
import com.es.core.exception.PhoneNotFoundException;
import com.es.core.model.Phone;
import com.es.core.service.impl.PhoneService;
import com.es.core.service.inter.CartService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.List;

@Component
public class AddToCartValidator implements Validator {

    @Resource
    private PhoneService phoneService;

    @Resource
    private CartService cartService;

    @Override
    public boolean supports(Class<?> aClass) {
        return AddToCartForm.class.equals(aClass);
    }

    @Override
    public void validate(Object validateObject, Errors errors) {
        int intQuantity;
        CartEntity cartEntity = new CartEntity();
        Phone phone = new Phone();
        AddToCartForm AddToCartForm = (AddToCartForm) validateObject;
        String quantity = AddToCartForm.getQuantity();
        Long phoneId = Long.valueOf(AddToCartForm.getPhoneId());
        phone.setId(phoneId);
        cartEntity.setPhone(phone);
        List<CartEntity> cartEntityList = cartService.getCart().getCartEntityList();

        try {
            intQuantity = Integer.valueOf(quantity);
        } catch (NumberFormatException e) {
            errors.reject("Not a number");
            return;
        }

        if (intQuantity <= 0) {
            errors.reject("Use number more then 0");
            return;
        }
        if (cartEntityList.contains(cartEntity)) {
            existInCart(cartEntityList, cartEntity, errors, intQuantity);
        } else {
            dontExistInCart(phoneId, errors, intQuantity);
        }
    }

    private void existInCart(List<CartEntity> cartEntityList, CartEntity cartEntity, Errors errors, Integer intQuantity) {
        int index = cartEntityList.indexOf(cartEntity);
        cartEntity = cartEntityList.get(index);
        if (cartEntity.getQuantity() + intQuantity > cartEntity.getPhone().getStock()) {
            errors.reject("Not enough stock");
        }
    }

    private void dontExistInCart(Long phoneId, Errors errors, Integer intQuantity) {
        try {
            Phone phone = phoneService.get(phoneId);
            if (intQuantity > phone.getStock()) {
                errors.reject("Available stock: " + phone.getStock());
            }
        } catch (PhoneNotFoundException e) {
            errors.reject("Phone not found");
        }
    }
}
