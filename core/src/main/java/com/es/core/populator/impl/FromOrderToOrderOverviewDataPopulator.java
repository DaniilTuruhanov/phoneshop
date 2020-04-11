package com.es.core.populator.impl;

import com.es.core.data.OrderOverviewData;
import com.es.core.data.PhoneData;
import com.es.core.model.CartEntity;
import com.es.core.model.OrderModel;
import com.es.core.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class FromOrderToOrderOverviewDataPopulator implements Populator<OrderModel, OrderOverviewData> {

    @Override
    public void populate(OrderModel order, OrderOverviewData orderOverviewData) {
        for (CartEntity cartEntity : order.getCartEntityList()) {
            PhoneData phoneData = new PhoneData();
            phoneData.setModel(cartEntity.getPhone().getModel());
            phoneData.setColors(cartEntity.getPhone().getColors());
            phoneData.setBrand(cartEntity.getPhone().getBrand());
            phoneData.setDisplaySizeInches(cartEntity.getPhone().getDisplaySizeInches());
            phoneData.setPrice(cartEntity.getPhone().getPrice());
            phoneData.setId(cartEntity.getPhone().getId());
            phoneData.setQuantity(cartEntity.getQuantity());
            orderOverviewData.getOrderEntityList().add(phoneData);
        }
        orderOverviewData.setId(order.getId());
        orderOverviewData.setDeliveryCost(order.getDeliveryCost());
        orderOverviewData.setSubtotalCost(order.getSubtotalCost());
        orderOverviewData.setTotalCost(order.getTotalCost());
        orderOverviewData.setDescription(order.getUserModel().getDescription());
        orderOverviewData.setAddress(order.getUserModel().getAddress());
        orderOverviewData.setFirstName(order.getUserModel().getFirstName());
        orderOverviewData.setLastName(order.getUserModel().getLastName());
        orderOverviewData.setNumber(order.getNumber());
        orderOverviewData.setPhone(order.getUserModel().getPhone());
        orderOverviewData.setTotalCost(order.getTotalCost());
        orderOverviewData.setDeliveryCost(order.getDeliveryCost());
        orderOverviewData.setTotalCost(order.getTotalCost());
    }
}
