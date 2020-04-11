package com.es.core.facade;

import com.es.core.converter.FromOrderToOrderOverviewDataConverter;
import com.es.core.data.OrderOverviewData;
import com.es.core.exception.OrderNotFoundException;
import com.es.core.model.OrderModel;
import com.es.core.service.OrderService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OrderOverviewFacade {

    @Resource
    private OrderService orderService;

    @Resource
    private FromOrderToOrderOverviewDataConverter fromOrderToOrderOverviewDataConverter;

    public OrderOverviewData getOrder(String id) throws OrderNotFoundException {
        OrderModel order = orderService.getOrder(id);
        OrderOverviewData orderOverviewData = fromOrderToOrderOverviewDataConverter.convert(order);
        return orderOverviewData;
    }
}