package com.es.core.services.interfaces;

import com.es.core.exceptions.PhoneNotFoundException;
import com.es.core.models.AddToCartModel;
import com.es.core.models.Cart;

import java.util.Map;

public interface CartService {
    Cart getCart();

    void addPhone(AddToCartModel addToCartModel) throws PhoneNotFoundException;

    void update(Map<Long, Long> items);

    void recalculateTotals();

    void remove(Long phoneId);
}
