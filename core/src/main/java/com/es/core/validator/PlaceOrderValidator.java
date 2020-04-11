package com.es.core.validator;

import com.es.core.exception.PhoneNotFoundException;
import com.es.core.form.PlaceOrderForm;
import com.es.core.model.Phone;
import com.es.core.service.impl.PhoneService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;

@Component
public class PlaceOrderValidator implements Validator {

    @Resource
    private PhoneService phoneService;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        PlaceOrderForm placeOrderForm = (PlaceOrderForm) o;
        for (int i = 0; i < placeOrderForm.getPlaceOrderId().size(); i++) {
            try {
                Phone phone = phoneService.get(placeOrderForm.getPlaceOrderId().get(i));
                int stock = phone.getStock();
                String model = phone.getModel();
                if (stock < placeOrderForm.getPlaceOrderQuantity().get(i)) {
                    errors.reject("quantity", "Sorry, but " + model + " expired");
                }
            } catch (PhoneNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}