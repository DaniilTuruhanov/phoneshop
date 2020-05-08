package com.es.core.validator;

import com.es.core.exception.PhoneNotFoundException;
import com.es.core.form.AddToCartForm;
import com.es.core.form.QuickOrderForm;
import com.es.core.model.CartEntity;
import com.es.core.model.Phone;
import com.es.core.model.QuickOrderEntity;
import com.es.core.service.CartService;
import com.es.core.service.impl.PhoneService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public void validate(Object validateObject, Errors errors) {
        CartEntity cartEntity = new CartEntity();
        Phone phone = new Phone();
        QuickOrderForm quickOrderForm = (QuickOrderForm) validateObject;
        List<QuickOrderEntity> quickOrderEntityList = quickOrderForm.getQuickOrderEntityList();
        for (int i = 0; quickOrderEntityList.size() > 0 && i < quickOrderEntityList.size(); i++) {
            String model = quickOrderEntityList.get(i).getModel();
            String quantity = quickOrderEntityList.get(i).getQuantity();
            if (!findInErrors(errors, i)) {
                int intQuantity = Integer.parseInt(quantity);
                phone.setModel(model);
                cartEntity.setPhone(phone);
                List<CartEntity> cartEntityList = cartService.getCart().getCartEntityList();
                if (cartEntityList.contains(cartEntity)) {
                    i = existInCart(cartEntityList, cartEntity, errors, intQuantity, quickOrderForm, i);
                } else {
                    i = absentInCart(model, errors, intQuantity, i, quickOrderForm);
                }
            } else {
                deleteFromQuickOrderForm(i, quickOrderForm);
                i--;
            }
        }
    }

    private boolean findInErrors(Errors errors, Integer i) {
        return errors.getAllErrors().stream().anyMatch(error -> Objects.requireNonNull(error.getCodes())[0].contains("quickOrderEntityList[" + i + "].model")
                || Objects.requireNonNull(error.getCodes())[0].contains("quickOrderEntityList[" + i + "].quantity"));
    }

    private void deleteFromQuickOrderForm(Integer i, QuickOrderForm quickOrderForm) {
        List<QuickOrderEntity> quickOrderEntityList = quickOrderForm.getQuickOrderEntityList();
        quickOrderEntityList.remove(quickOrderEntityList.get(i));
        quickOrderForm.setQuickOrderEntityList(quickOrderEntityList);
    }

    private int existInCart(List<CartEntity> cartEntityList, CartEntity cartEntity, Errors errors, Integer
            intQuantity, QuickOrderForm quickOrderForm, Integer i) {
        int index = cartEntityList.indexOf(cartEntity);
        cartEntity = cartEntityList.get(index);
        if (cartEntity.getQuantity() + intQuantity > cartEntity.getPhone().getStock()) {
            errors.rejectValue("quickOrderEntityList[" + i + "].quantity", "", "not enough stock");
            deleteFromQuickOrderForm(i, quickOrderForm);
            i--;
        }
        return i;
    }

    private int absentInCart(String phoneModel, Errors errors, Integer intQuantity, Integer i, QuickOrderForm quickOrderForm) {
        try {
            Phone phone = phoneService.get(phoneModel);
            if (intQuantity > phone.getStock()) {
                errors.rejectValue("quickOrderEntityList[" + i + "].quantity", "", "Available stock: " + phone.getStock());
                deleteFromQuickOrderForm(i, quickOrderForm);
                i--;
            }
        } catch (PhoneNotFoundException e) {
            errors.rejectValue("quickOrderEntityList[" + i + "].model", "", "Phone not found");
            deleteFromQuickOrderForm(i, quickOrderForm);
            i--;
        }
        return i;
    }
}
