package com.es.core.cart;

import com.es.core.phone.Phone;

import java.util.Map;

public interface CartService {

    Cart getCart();

    void addPhone(Long phoneId, Long quantity);

    void update(Map<Long, Long> items);

    void remove(Long phoneId);
}
