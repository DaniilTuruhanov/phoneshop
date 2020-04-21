package com.es.core.converter;

import com.es.core.data.AdminDetailsData;
import com.es.core.model.OrderModel;
import com.es.core.populator.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class FromOrderToAdminDetailsDataConverter implements Converter<OrderModel, AdminDetailsData> {

    private List<Populator> populators;

    @Override
    public AdminDetailsData convert(OrderModel order) {
        AdminDetailsData adminEntityData = new AdminDetailsData();
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
