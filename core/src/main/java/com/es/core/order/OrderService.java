package com.es.core.order;

import com.es.core.cart.Cart;

public interface OrderService {
    Order createOrder(Cart cart);

    void placeOrder(Order order) throws OutOfStockException;
}
