package com.es.core.service;

import com.es.core.exception.PhoneNotFoundException;
import com.es.core.model.AddToCartModel;
import com.es.core.model.Cart;

import java.util.Map;

public interface CartService {
    Cart getCart();

    void addPhone(AddToCartModel addToCartModel) throws PhoneNotFoundException;

    void update(Map<Long, Long> items);

    void recalculateTotals();

    void remove(Long phoneId);
}
