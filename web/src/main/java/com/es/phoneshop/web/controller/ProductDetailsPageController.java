package com.es.phoneshop.web.controller;

import com.es.core.data.PhoneData;
import com.es.core.exception.PhoneNotFoundException;
import com.es.core.facade.PhoneFacade;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/products/{phoneId}")
public class ProductDetailsPageController {

    @Resource
    private PhoneFacade phoneFacade;

    @GetMapping
    public String showProductList(Model model, @PathVariable String phoneId) throws PhoneNotFoundException {
        PhoneData phoneData = phoneFacade.getPhone(phoneId);
        model.addAttribute("phone", phoneData);
        return "productPage";
    }

}
