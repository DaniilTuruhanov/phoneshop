package com.es.core.converter;

import com.es.core.data.AdminData;
import com.es.core.model.OrderModel;
import com.es.core.populator.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class FromOrderToAdminDataConverter implements Converter<OrderModel, AdminData> {

    private List<Populator> populators;

    @Override
    public AdminData convert(OrderModel order) {
        AdminData adminOrderData = new AdminData();
        for (Populator populator : populators) {
            populator.populate(order, adminOrderData);
        }
        return adminOrderData;
    }

    public List<Populator> getPopulators() {
        return populators;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
