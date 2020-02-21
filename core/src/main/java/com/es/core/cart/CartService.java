package com.es.core.cart;

import com.es.core.exceptions.PhoneNotFoundException;

import java.util.Map;

public interface CartService {
    Cart getCart();

    void addPhone(Long phoneId, Integer quantity) throws PhoneNotFoundException;

    void update(Map<Long, Long> items);

    void remove(Long phoneId);
}
