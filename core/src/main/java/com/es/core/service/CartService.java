package com.es.core.service;

import com.es.core.exception.PhoneNotFoundException;
import com.es.core.model.AddToCartModel;
import com.es.core.model.CartModel;
import com.es.core.model.UpdateCartModel;

public interface CartService {
    CartModel getCart();

    void addPhone(AddToCartModel addToCartModel) throws PhoneNotFoundException;

    void update(UpdateCartModel updateCartModel);

    void recalculateTotals();

    void remove(Long phoneId) throws PhoneNotFoundException;

    void cleanCart();
}
