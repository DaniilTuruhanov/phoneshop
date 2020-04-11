package com.es.core.service.impl;

import com.es.core.dao.OrderDao;
import com.es.core.exception.OrderNotFoundException;
import com.es.core.model.CartModel;
import com.es.core.model.OrderModel;
import com.es.core.model.Status;
import com.es.core.service.CartService;
import com.es.core.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Component
@PropertySource("classpath:application.properties")
public class HttpSessionOrderService implements OrderService {

    @Value("${delivery.price}")
    private BigDecimal price;

    @Resource
    private CartService cartService;

    @Resource
    private OrderDao orderDao;

    @Override
    public OrderModel getOrder(String id) throws OrderNotFoundException {
        return orderDao.getOrder(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public OrderModel getOrder(Integer number) throws OrderNotFoundException {
        return orderDao.getOrder(number).orElseThrow(() -> new OrderNotFoundException(number.toString()));
    }

    @Override
    public void placeOrder(OrderModel order) {
        orderDao.saveOrder(order);
        cartService.cleanCart();
    }

    @Override
    public OrderModel getOrder() {
        CartModel cart = cartService.getCart();
        OrderModel order = new OrderModel();
        order.setCartEntityList(cart.getCartEntityList());
        order.setSubtotalCost(cart.getTotalCost());
        order.setDeliveryCost(price);
        order.setStatus(Status.NEW);
        order.setTotalCost(order.getDeliveryCost().add(order.getSubtotalCost()));
        return order;
    }

    @Override
    public List<OrderModel> getOrderList() {
        return orderDao.getOrderList();
    }

    @Override
    public void changeStatus(Status status, Integer number) {
        orderDao.changeStatus(status, number);
    }


}
