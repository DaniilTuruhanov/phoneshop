package com.es.core.service;

import com.es.core.exception.PhoneNotFoundException;
import com.es.core.model.CartModel;

import java.util.List;

public interface CartService {
    CartModel getCart();

    void addPhone(Long id, Integer quantity) throws PhoneNotFoundException;

    void update(List<String> id, List<String> quantity);

    void recalculateTotals();

    void remove(Long phoneId) throws PhoneNotFoundException;

    void cleanCart();
}
