package com.es.core.validators;

import com.es.core.cart.AddToCartPhone;
import com.es.core.cart.CartEntity;
import com.es.core.exceptions.PhoneNotFoundException;
import com.es.core.phone.Phone;
import com.es.core.phone.PhoneService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.List;

@Component
public class AddToCartValidator implements Validator {

    @Resource
    private PhoneService phoneService;

    @Override
    public boolean supports(Class<?> aClass) {
        return AddToCartPhone.class.equals(aClass);
    }

    @Override
    public void validate(Object validateObject, Errors errors) {
        AddToCartPhone addToCartPhone = (AddToCartPhone) validateObject;
        String quantity = addToCartPhone.getQuantity();
        int intQuantity;
        Long phoneId = addToCartPhone.getPhoneId();
        CartEntity cartEntity = new CartEntity();
        Phone phone = new Phone();
        phone.setId(phoneId);
        cartEntity.setPhone(phone);
        List<CartEntity> phoneStocks = addToCartPhone.getCart().getCartEntityList();

        try {
            intQuantity = Integer.valueOf(quantity);
        } catch (NumberFormatException e) {
            errors.reject("Not a number");
            return;
        }
        if (phoneStocks.contains(cartEntity)) {
            int index = phoneStocks.indexOf(cartEntity);
            cartEntity = phoneStocks.get(index);
            if (cartEntity.getReserved() + intQuantity > cartEntity.getPhone().getStock()) {
                errors.reject("Available stock: " + cartEntity.getPhone().getStock());
            }
        } else {
            try {
                phone = phoneService.get(phoneId);
                if (intQuantity > phone.getStock()) {
                    errors.reject("Available stock: " + phone.getStock());
                }
            } catch (PhoneNotFoundException e) {
                errors.reject("Phone not found");
            }
        }
    }
}
