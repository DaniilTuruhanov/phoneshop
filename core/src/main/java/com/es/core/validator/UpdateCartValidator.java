package com.es.core.validator;

import com.es.core.form.AddToCartForm;
import com.es.core.form.UpdateCartForm;
import com.es.core.model.CartEntity;
import com.es.core.model.Phone;
import com.es.core.service.CartService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UpdateCartValidator implements Validator {

    @Resource
    private CartService cartService;

    @Override
    public boolean supports(Class<?> aClass) {
        return AddToCartForm.class.equals(aClass);
    }

    @Override
    public void validate(Object validateObject, Errors errors) {
        List<CartEntity> cartEntityList = cartService.getCart().getCartEntityList();
        Phone phone = new Phone();
        int intQuantity;
        UpdateCartForm updateCartForm = (UpdateCartForm) validateObject;

        for (int i = 0; i < updateCartForm.getPhonesId().size(); i++) {
            String stringPhoneId = updateCartForm.getPhonesId().get(i);
            try {
                CartEntity cartEntity = new CartEntity();
                Long phoneId = Long.valueOf(stringPhoneId);
                phone.setId(phoneId);
                cartEntity.setPhone(phone);
                intQuantity = Integer.parseInt(updateCartForm.getQuantity().get(i));
                int index = cartEntityList.indexOf(cartEntity);
                cartEntity = cartEntityList.get(index);
                if (intQuantity <= 0) {
                    errors.reject(stringPhoneId, "Use number more then 0");
                } else {
                    if (intQuantity > cartEntity.getPhone().getStock()) {
                        errors.reject(stringPhoneId, "Available stock: " + cartEntity.getPhone().getStock());
                    }
                }
            } catch (NumberFormatException e) {
                errors.reject(stringPhoneId, "Not a number");
            }
        }
    }
}
