package com.es.core.populator.impl;

import com.es.core.data.AdminOrderData;
import com.es.core.data.PhoneData;
import com.es.core.model.CartEntity;
import com.es.core.model.OrderModel;
import com.es.core.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class FromOrderToAdminOrderDataPopulator implements Populator<OrderModel, AdminOrderData> {

    @Override
    public void populate(OrderModel order, AdminOrderData adminOrderData) {
        for (CartEntity cartEntity : order.getCartEntityList()) {
            PhoneData orderEntityData = new PhoneData();
            orderEntityData.setColors(cartEntity.getPhone().getColors());
            orderEntityData.setBrand(cartEntity.getPhone().getBrand());
            orderEntityData.setModel(cartEntity.getPhone().getModel());
            orderEntityData.setDisplaySizeInches(cartEntity.getPhone().getDisplaySizeInches());
            orderEntityData.setPrice(cartEntity.getPhone().getPrice());
            orderEntityData.setId(cartEntity.getPhone().getId());
            orderEntityData.setQuantity(cartEntity.getQuantity());
            adminOrderData.getOrderEntityList().add(orderEntityData);
        }
        adminOrderData.setSubtotalCost(order.getSubtotalCost());
        adminOrderData.setDeliveryCost(order.getDeliveryCost());
        adminOrderData.setDescription(order.getUserModel().getDescription());
        adminOrderData.setTotalCost(order.getTotalCost());
        adminOrderData.setLastName(order.getUserModel().getLastName());
        adminOrderData.setFirstName(order.getUserModel().getFirstName());
        adminOrderData.setAddress(order.getUserModel().getAddress());
        adminOrderData.setNumber(order.getNumber());
        adminOrderData.setPhone(order.getUserModel().getPhone());
        adminOrderData.setTotalCost(order.getTotalCost());
        adminOrderData.setDeliveryCost(order.getDeliveryCost());
        adminOrderData.setTotalCost(order.getTotalCost());
        adminOrderData.setStatus(order.getStatus());
    }
}
