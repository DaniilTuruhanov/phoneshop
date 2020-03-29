package com.es.core.populator.impl;

import com.es.core.data.OrderData;
import com.es.core.data.PhoneData;
import com.es.core.model.CartEntity;
import com.es.core.model.OrderModel;
import com.es.core.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class FromOrderToOrderDataPopulator implements Populator<OrderModel, OrderData> {

    @Override
    public void populate(OrderModel order, OrderData orderData) {
        for (CartEntity cartEntity : order.getCartEntityList()) {
            PhoneData phoneData = new PhoneData();
            phoneData.setModel(cartEntity.getPhone().getModel());
            phoneData.setBrand(cartEntity.getPhone().getBrand());
            phoneData.setColors(cartEntity.getPhone().getColors());
            phoneData.setDisplaySizeInches(cartEntity.getPhone().getDisplaySizeInches());
            phoneData.setPrice(cartEntity.getPhone().getPrice());
            phoneData.setId(cartEntity.getPhone().getId());
            phoneData.setQuantity(cartEntity.getQuantity());
            orderData.getOrderEntityList().add(phoneData);
        }
        orderData.setId(order.getId());
        orderData.setDeliveryCost(order.getDeliveryCost());
        orderData.setSubtotalCost(order.getSubtotalCost());
        orderData.setTotalCost(order.getTotalCost());
    }
}
