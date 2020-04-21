package com.es.phoneshop.web.controller;

import com.es.core.data.AdminData;
import com.es.core.data.AdminDetailsData;
import com.es.core.exception.OrderNotFoundException;
import com.es.core.facade.AdminFacade;
import com.es.core.model.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminPageController {

    @Resource
    private AdminFacade adminFacade;

    @GetMapping
    public String getListOrders(Model model) {
        List<AdminData> adminDataList = adminFacade.getOrderList();
        model.addAttribute("orderList", adminDataList);
        return "admin";
    }

    @GetMapping(value = "/{orderNumber}")
    public String getOrder(@PathVariable Integer orderNumber, Model model) throws OrderNotFoundException {
        AdminDetailsData adminOrderData = adminFacade.getAdminOrderData(orderNumber);
        model.addAttribute("order", adminOrderData);
        return "orderAdmin";
    }

    @PostMapping(value = "/{orderNumber}")
    public String changeStatus(@RequestParam String status, Model model, @PathVariable Integer orderNumber) throws OrderNotFoundException {
        AdminDetailsData adminOrderData = adminFacade.changeStatus(Status.valueOf(status), orderNumber);
        model.addAttribute("order", adminOrderData);
        return "orderAdmin";
    }
}
