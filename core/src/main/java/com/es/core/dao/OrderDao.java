package com.es.core.dao;

import com.es.core.model.OrderModel;
import com.es.core.model.Status;

import java.util.List;
import java.util.Optional;

public interface OrderDao {

    void saveOrder(OrderModel order);

    Optional<OrderModel> getOrder(String secureId);

    Optional<OrderModel> getOrder(Integer number);

    List<OrderModel> getOrderList();

    void changeStatus(Status status, Integer number);
}
