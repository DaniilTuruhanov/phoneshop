package com.es.phoneshop.web.controller;

import com.es.core.data.CartData;
import com.es.core.exception.PhoneNotFoundException;
import com.es.core.facade.CartFacade;
import com.es.core.form.UpdateCartForm;
import com.es.core.validator.UpdateCartValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartPageController {

    @Resource
    private CartFacade cartFacade;

    @Resource
    private UpdateCartValidator updateCartValidator;

    @GetMapping
    public String getCart(Model model) {
        CartData cartData = cartFacade.getCartData();
        model.addAttribute("cart", cartData);
        return "cart";
    }

    @DeleteMapping
    public String deleteCartItem(@RequestParam Long phoneId, Model model) throws PhoneNotFoundException {
        cartFacade.deleteFromCart(phoneId);
        CartData cartData = cartFacade.getCartData();
        model.addAttribute("cart", cartData);
        return "cart";
    }

    @PutMapping
    public String updateCart(@ModelAttribute UpdateCartForm updateCartForm, Model model, BindingResult result) {
        updateCartValidator.validate(updateCartForm, result);
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            model.addAttribute("errors", allErrors);
        } else {
            cartFacade.updateCart(updateCartForm);
        }
        CartData cartData = cartFacade.getCartData();
        model.addAttribute("cart", cartData);
        return "cart";
    }
}
