package com.es.core.service;

import com.es.core.exception.OrderNotFoundException;
import com.es.core.model.OrderModel;

import java.util.List;

public interface OrderService {
    OrderModel getOrder(String id) throws OrderNotFoundException;

    OrderModel getOrder(Integer number) throws OrderNotFoundException;

    void placeOrder(OrderModel order);

    OrderModel getOrder();

    List<OrderModel> getOrderList();

    void changeStatus(String status, Integer number);
}
