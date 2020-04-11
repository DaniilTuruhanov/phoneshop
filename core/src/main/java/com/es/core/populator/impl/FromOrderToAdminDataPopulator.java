package com.es.core.populator.impl;

import com.es.core.data.AdminData;
import com.es.core.model.OrderModel;
import com.es.core.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class FromOrderToAdminDataPopulator implements Populator<OrderModel, AdminData> {

    @Override
    public void populate(OrderModel order, AdminData adminEntityData) {
        adminEntityData.setTotalPrice(order.getTotalCost());
        adminEntityData.setAddress(order.getUserModel().getAddress());
        adminEntityData.setCustomer(order.getUserModel().getFirstName() + " " + order.getUserModel().getLastName());
        adminEntityData.setNumber(order.getNumber());
        adminEntityData.setPhone(order.getUserModel().getPhone());
        adminEntityData.setDate(order.getUserModel().getDate());
        adminEntityData.setStatus(order.getStatus());
    }
}
