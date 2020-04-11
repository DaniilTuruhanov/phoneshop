package com.es.phoneshop.web.controller;

import com.es.core.data.OrderData;
import com.es.core.data.OrderOverviewData;
import com.es.core.exception.OrderNotFoundException;
import com.es.core.exception.PhoneNotFoundException;
import com.es.core.facade.OrderFacade;
import com.es.core.form.PlaceOrderForm;
import com.es.core.form.UserInfoForm;
import com.es.core.validator.PlaceOrderValidator;
import com.es.core.validator.UserInfoValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/order")
public class OrderPageController {

    @Resource
    private OrderFacade orderFacade;

    @Resource
    private PlaceOrderValidator placeOrderValidator;

    @Resource
    private UserInfoValidator userInfoValidator;

    @GetMapping
    private String getOrderPage(Model model) {
        OrderData orderData = orderFacade.getOrder();
        model.addAttribute("order", orderData);
        return "order";
    }

    @PostMapping
    private String placeOrder(@ModelAttribute UserInfoForm userForm, @ModelAttribute PlaceOrderForm placeOrderForm, Model model, BindingResult result) throws OrderNotFoundException, PhoneNotFoundException {
        userInfoValidator.validate(userForm, result);
        if (result.hasErrors()) {
            OrderData orderData = orderFacade.getOrder();
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("order", orderData);
            return "order";
        }
        placeOrderValidator.validate(placeOrderForm, result);
        if (result.hasErrors()) {
            orderFacade.deleteFromCart(placeOrderForm);
            model.addAttribute("errors", result.getAllErrors());
            OrderData orderData = orderFacade.getOrder();
            model.addAttribute("order", orderData);
            return "order";
        }
        String id = orderFacade.placeOrder(userForm);
        return "redirect:/orderOverview/" + id;
    }
}
