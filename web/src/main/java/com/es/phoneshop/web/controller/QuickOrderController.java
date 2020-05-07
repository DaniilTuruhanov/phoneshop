package com.es.phoneshop.web.controller;

import com.es.core.exception.PhoneNotFoundException;
import com.es.core.facade.CartFacade;
import com.es.core.form.QuickOrderForm;
import com.es.core.validator.QuickOrderAddToCartValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/quickOrder")
public class QuickOrderController {

    @Resource
    private QuickOrderAddToCartValidator quickOrderAddToCartValidator;

    @Resource
    private CartFacade cartFacade;

    @GetMapping
    public String getQuickOrderPage() {
        return "quickOrder";
    }

    @PostMapping
    public String addPhoneToCart(@ModelAttribute QuickOrderForm quickOrderForm, Model model, BindingResult errors) throws PhoneNotFoundException {
        quickOrderAddToCartValidator.validate(quickOrderForm, errors);
        int count = cartFacade.addToCart(quickOrderForm);
        model.addAttribute("count", count);
        model.addAttribute("errors", errors.getAllErrors());
        return "quickOrder";
    }

}
