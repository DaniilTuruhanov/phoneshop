package com.es.phoneshop.web.pages;

import com.es.core.cart.AddToCartPhone;
import com.es.core.cart.Cart;
import com.es.core.cart.CartService;
import com.es.core.exceptions.PhoneNotFoundException;
import com.es.core.validators.AddToCartValidator;
import com.es.core.validators.ErrorMap;
import com.es.core.validators.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.awt.*;

@Controller
@RequestMapping(value = "/ajaxCart")
public class AjaxCartController {
    @Resource
    private CartService cartService;

    @Autowired
    private AddToCartValidator addToCartValidator;

    @ResponseBody
    @PostMapping
    public String addPhone(@RequestParam String phoneId, @RequestParam String quantity, HttpSession session) throws PhoneNotFoundException, JsonProcessingException {
        Cart cart = cartService.getCart(session);
        ObjectMapper objectMapper = new ObjectMapper();
        AddToCartPhone addToCartPhone = new AddToCartPhone(cart, quantity, Long.valueOf(phoneId));
        ErrorMap errorMap = new ErrorMap();

        addToCartValidator.validate(addToCartPhone, errorMap);
        if (errorMap.getErrorMap().isEmpty()) {
            cartService.addPhone(cart, Long.valueOf(phoneId), Integer.valueOf(quantity));
            String message = cart.getTotalCost().toString();
            ErrorMessage errorMessage = new ErrorMessage(true, message);
            String error = objectMapper.writeValueAsString(errorMessage);
            return error;
        } else {
            String errorString = errorMap.getErrorMap().get("quantity" + phoneId).get(0);
            ErrorMessage errorMessage = new ErrorMessage(false, errorString);
            String error = objectMapper.writeValueAsString(errorMessage);
            return error;
        }
    }
}
