package com.es.core.service;

import com.es.core.exception.PhoneNotFoundException;
import com.es.core.model.AddToCartModel;
import com.es.core.model.Cart;
import com.es.core.model.UpdateCartModel;

public interface CartService {
    Cart getCart();

    void addPhone(AddToCartModel addToCartModel) throws PhoneNotFoundException;

    void update(UpdateCartModel updateCartModel);

    void recalculateTotals();

    void remove(Long phoneId) throws PhoneNotFoundException;

    void cleanCart();
}
