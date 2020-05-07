package com.es.core.validator;

import com.es.core.exception.PhoneNotFoundException;
import com.es.core.form.AddToCartForm;
import com.es.core.form.QuickOrderForm;
import com.es.core.model.CartEntity;
import com.es.core.model.Phone;
import com.es.core.service.CartService;
import com.es.core.service.impl.PhoneService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuickOrderAddToCartValidator implements Validator {

    @Resource
    private PhoneService phoneService;

    @Resource
    private CartService cartService;

    @Override
    public boolean supports(Class<?> aClass) {
        return AddToCartForm.class.equals(aClass);
    }

    private static List<String> deleteFromQuickOrder= new ArrayList<>();

    @Override
    public void validate(Object validateObject, Errors errors) {
        int intQuantity;
        CartEntity cartEntity = new CartEntity();
        Phone phone = new Phone();
        QuickOrderForm quickOrderForm = (QuickOrderForm) validateObject;
        for (int i = 0; i < quickOrderForm.getPhoneModel().size(); i++) {
            if (!quickOrderForm.getPhoneModel().get(i).equals("") || !quickOrderForm.getPhoneQuantity().get(i).equals("")) {
                String quantity = quickOrderForm.getPhoneQuantity().get(i);
                String phoneModel = quickOrderForm.getPhoneModel().get(i);
                phone.setModel(phoneModel);
                cartEntity.setPhone(phone);
                List<CartEntity> cartEntityList = cartService.getCart().getCartEntityList();
                try {
                    intQuantity = Integer.valueOf(quantity);
                } catch (NumberFormatException e) {
                    errors.reject(phoneModel, "Not a number");
                    deleteFromQuickOrder.add(phoneModel);
                    break;
                }

                if (intQuantity <= 0) {
                    errors.reject(phoneModel, "Use number more then 0");
                    deleteFromQuickOrder.add(phoneModel);
                    break;
                }
                if (cartEntityList.contains(cartEntity)) {
                    existInCart(cartEntityList, cartEntity, errors, intQuantity, phoneModel);
                } else {
                    dontExistInCart(phoneModel, errors, intQuantity);
                }
            }
        }
        for (int i = 0; i < deleteFromQuickOrder.size(); i++) {
            if (quickOrderForm.getPhoneModel().contains(deleteFromQuickOrder.get(i))) {
                int index = quickOrderForm.getPhoneModel().indexOf(deleteFromQuickOrder.get(i));
                quickOrderForm.getPhoneQuantity().remove(index);
                quickOrderForm.getPhoneModel().remove(index);
            }
        }
    }

    private void existInCart(List<CartEntity> cartEntityList, CartEntity cartEntity, Errors errors, Integer intQuantity, String phoneModel) {
        int index = cartEntityList.indexOf(cartEntity);
        cartEntity = cartEntityList.get(index);
        if (cartEntity.getQuantity() + intQuantity > cartEntity.getPhone().getStock()) {
            errors.reject(phoneModel, "Not enough stock");
            deleteFromQuickOrder.add(phoneModel);
        }
    }

    private void dontExistInCart(String phoneModel, Errors errors, Integer intQuantity) {
        try {
            Phone phone = phoneService.get(phoneModel);
            if (intQuantity > phone.getStock()) {
                errors.reject(phoneModel, "Available stock: " + phone.getStock());
                deleteFromQuickOrder.add(phoneModel);
            }
        } catch (PhoneNotFoundException e) {
            errors.reject(phoneModel, "Phone not found");
            deleteFromQuickOrder.add(phoneModel);
        }
    }
}
