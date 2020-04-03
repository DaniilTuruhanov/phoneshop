package com.es.core.service;

import com.es.core.exception.OrderNotFoundException;
import com.es.core.model.OrderModel;
import com.es.core.model.Status;

import java.util.List;

public interface OrderService {
    OrderModel getOrder(String id) throws OrderNotFoundException;

    OrderModel getOrder(Integer number) throws OrderNotFoundException;

    void placeOrder(OrderModel order);

    OrderModel getOrder();

    List<OrderModel> getOrderList();

    void changeStatus(Status status, Integer number);
}
