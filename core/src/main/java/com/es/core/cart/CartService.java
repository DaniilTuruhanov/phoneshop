package com.es.core.cart;

import com.es.core.exceptions.PhoneNotFoundException;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface CartService {

    Cart getCart(HttpSession session);

    void addPhone(Cart cart, Long phoneId, Integer quantity) throws PhoneNotFoundException;

    void update(Map<Long, Long> items);

    void remove(Long phoneId);
}
