package com.es.core.converter;

import com.es.core.data.OrderOverviewData;
import com.es.core.model.OrderModel;
import com.es.core.populator.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class FromOrderToOrderOverviewDataConverter implements Converter<OrderModel, OrderOverviewData> {

    private List<Populator> populators;

    @Override
    public OrderOverviewData convert(OrderModel order) {
        OrderOverviewData orderOverviewData = new OrderOverviewData();
        for (Populator populator : populators) {
            populator.populate(order, orderOverviewData);
        }
        return orderOverviewData;
    }

    public List<Populator> getPopulators() {
        return populators;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
