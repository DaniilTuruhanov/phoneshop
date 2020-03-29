package com.es.phoneshop.web.controller;

import com.es.core.data.OrderOverviewData;
import com.es.core.exception.OrderNotFoundException;
import com.es.core.facade.OrderOverviewFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/orderOverview")
public class OrderOverviewPageController {

    @Resource
    private OrderOverviewFacade orderOverviewFacade;

    @GetMapping(value = "/{orderId}")
    public String getOrderOverview(@PathVariable String orderId, Model model) throws OrderNotFoundException {
        OrderOverviewData orderOverviewData = orderOverviewFacade.getOrder(orderId);
        model.addAttribute("order", orderOverviewData);
        return "orderOverview";
    }
}
