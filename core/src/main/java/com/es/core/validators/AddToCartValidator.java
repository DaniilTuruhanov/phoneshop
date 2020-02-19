package com.es.core.validators;

import com.es.core.cart.AddToCartPhone;
import com.es.core.cart.StockService;
import com.es.core.exceptions.PhoneNotFoundException;
import com.es.core.phone.Phone;
import com.es.core.phone.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


import java.util.List;

@Component
public class AddToCartValidator implements Validator<AddToCartPhone> {
    @Autowired
    private StockService stockService;

    @Override
    public void validate(AddToCartPhone validateObject, ErrorMap errorMap) {
        AddToCartPhone addToCartPhone = validateObject;
        String quantity = addToCartPhone.getQuantity();
        int intQuantity;
        Long phoneId = addToCartPhone.getPhoneId();
        Stock stock = new Stock();
        Phone phone = new Phone();
        phone.setId(phoneId);
        stock.setPhone(phone);
        List<Stock> phoneStocks = addToCartPhone.getCart().getPhoneStocks();

        try {
            intQuantity = Integer.valueOf(quantity);
        } catch (NumberFormatException e) {
            errorMap.addError("quantity" + addToCartPhone.getPhoneId(), "Not a number");
            return;
        }
        if (phoneStocks.contains(stock)) {
            stock = phoneStocks.get(phoneStocks.indexOf(stock));
            if (stock.getReserved() + intQuantity > stock.getStock()) {
                errorMap.addError("quantity" + phoneId, "Available stock: " + stock.getStock());
            }
        } else {
            try {
                stock = stockService.get(phoneId);
                if (intQuantity > stock.getStock()) {
                    errorMap.addError("quantity" + phoneId, "Available stock: " + stock.getStock());
                }
            } catch (PhoneNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
