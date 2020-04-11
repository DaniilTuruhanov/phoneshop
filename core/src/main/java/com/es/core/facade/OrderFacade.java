package com.es.core.facade;

import com.es.core.converter.FromOrderToOrderDataConverter;
import com.es.core.converter.FromUserFormToModelConverter;
import com.es.core.data.OrderData;
import com.es.core.data.OrderOverviewData;
import com.es.core.exception.OrderNotFoundException;
import com.es.core.exception.PhoneNotFoundException;
import com.es.core.form.PlaceOrderForm;
import com.es.core.form.UserInfoForm;
import com.es.core.model.OrderModel;
import com.es.core.model.Phone;
import com.es.core.model.UserModel;
import com.es.core.service.CartService;
import com.es.core.service.OrderService;
import com.es.core.service.impl.PhoneService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class OrderFacade {

    @Resource
    private OrderService orderService;

    @Resource
    private CartService cartService;

    @Resource
    private FromOrderToOrderDataConverter fromOrderToOrderDataConverter;

    @Resource
    private FromUserFormToModelConverter fromUserFormToModelConverter;

    @Resource
    private PhoneService phoneService;

    public OrderData getOrder() {
        OrderModel order = orderService.getOrder();
        OrderData orderData = fromOrderToOrderDataConverter.convert(order);
        return orderData;
    }

    public String placeOrder(UserInfoForm userForm) {
        UserModel userModel = fromUserFormToModelConverter.convert(userForm);
        OrderModel order = orderService.getOrder();
        order.setUserModel(userModel);
        String id = UUID.randomUUID().toString();
        order.setId(id);
        orderService.placeOrder(order);
        return id;
    }

    public void deleteFromCart(PlaceOrderForm placeOrderForm) throws PhoneNotFoundException {
        for (int i = 0; i < placeOrderForm.getPlaceOrderId().size(); i++) {
            Phone phone = phoneService.get(placeOrderForm.getPlaceOrderId().get(i));
            int stock = phone.getStock();
            if (stock < placeOrderForm.getPlaceOrderQuantity().get(i)) {
                cartService.remove(phone.getId());
            }
        }
    }
}