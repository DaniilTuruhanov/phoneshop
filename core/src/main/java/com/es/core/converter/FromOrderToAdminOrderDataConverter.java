package com.es.core.converter;

import com.es.core.data.AdminOrderData;
import com.es.core.model.OrderModel;
import com.es.core.populator.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class FromOrderToAdminOrderDataConverter implements Converter<OrderModel, AdminOrderData> {

    private List<Populator> populators;

    @Override
    public AdminOrderData convert(OrderModel order) {
        AdminOrderData adminEntityData = new AdminOrderData();
        for (Populator populator : populators) {
            populator.populate(order, adminEntityData);
        }
        return adminEntityData;
    }

    public List<Populator> getPopulators() {
        return populators;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
