package com.es.core.facade;

import com.es.core.converter.FromOrderToAdminDataConverter;
import com.es.core.converter.FromOrderToAdminDetailsDataConverter;
import com.es.core.data.AdminData;
import com.es.core.data.AdminDetailsData;
import com.es.core.exception.OrderNotFoundException;
import com.es.core.model.OrderModel;
import com.es.core.model.Status;
import com.es.core.service.OrderService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminFacade {

    @Resource
    private OrderService orderService;

    @Resource
    private FromOrderToAdminDetailsDataConverter fromOrderToAdminOrderDataConverter;

    @Resource
    private FromOrderToAdminDataConverter fromOrderToAdminDataConverter;

    public List<AdminData> getOrderList() {
        List<AdminData> adminEntityDataList = new ArrayList<>();
        List<OrderModel> orderList = orderService.getOrderList();
        for (OrderModel order : orderList) {
            adminEntityDataList.add(fromOrderToAdminDataConverter.convert(order));
        }
        return adminEntityDataList;
    }

    public AdminDetailsData getAdminOrderData(Integer number) throws OrderNotFoundException {
        OrderModel order = orderService.getOrder(number);
        return fromOrderToAdminOrderDataConverter.convert(order);
    }

    public AdminDetailsData changeStatus(Status status, Integer number) throws OrderNotFoundException {
        orderService.changeStatus(status, number);
        OrderModel order = orderService.getOrder(number);
        return fromOrderToAdminOrderDataConverter.convert(order);
    }

}
