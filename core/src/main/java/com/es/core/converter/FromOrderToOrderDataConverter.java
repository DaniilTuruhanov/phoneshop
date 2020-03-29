package com.es.core.converter;

import com.es.core.data.OrderData;
import com.es.core.model.OrderModel;
import com.es.core.populator.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class FromOrderToOrderDataConverter implements Converter<OrderModel, OrderData> {

    private List<Populator> populators;

    @Override
    public OrderData convert(OrderModel order) {
        OrderData orderData = new OrderData();
        for (Populator populator : populators) {
            populator.populate(order, orderData);
        }
        return orderData;
    }

    public List<Populator> getPopulators() {
        return populators;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
