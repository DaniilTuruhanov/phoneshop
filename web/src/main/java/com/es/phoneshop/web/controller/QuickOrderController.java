package com.es.phoneshop.web.controller;

import com.es.core.exception.PhoneNotFoundException;
import com.es.core.facade.CartFacade;
import com.es.core.form.QuickOrderForm;
import com.es.core.model.QuickOrderEntity;
import com.es.core.validator.QuickOrderAddToCartValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@Validated
@RequestMapping(value = "/quickOrder")
public class QuickOrderController {

    @Resource
    private QuickOrderAddToCartValidator quickOrderAddToCartValidator;

    @Resource
    private CartFacade cartFacade;

    @GetMapping
    public String getQuickOrderPage(Model model) {
        model.addAttribute("quickOrderForm", new QuickOrderForm());
        return "quickOrder";
    }

    @PostMapping
    public String addPhoneToCart(@Valid @ModelAttribute("quickOrderForm") QuickOrderForm quickOrderForm, BindingResult errors, Model model) throws PhoneNotFoundException {
        quickOrderAddToCartValidator.validate(quickOrderForm, errors);
        for (int i = 0; i < quickOrderForm.getQuickOrderEntityList().size(); i++) {
            cartFacade.addToCart(quickOrderForm.getQuickOrderEntityList().get(i));
        }
        model.addAttribute("count", quickOrderForm.getQuickOrderEntityList().size());
        return "quickOrder";
    }
}
