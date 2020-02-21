package com.es.phoneshop.web.pages;

import com.es.core.cart.AddToCartPhone;
import com.es.core.cart.Cart;
import com.es.core.cart.CartService;
import com.es.core.exceptions.PhoneNotFoundException;
import com.es.core.phone.IdAndQuanityPhone;
import com.es.core.validators.AddToCartValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/ajaxCart", produces = MediaType.APPLICATION_JSON_VALUE)
public class AjaxCartController {

    @Resource
    private CartService cartService;

    @Resource
    private AddToCartValidator addToCartValidator;

    @PostMapping
    public ResponseEntity addPhone(@ModelAttribute IdAndQuanityPhone idAndQuanityPhone, BindingResult result) throws PhoneNotFoundException, JsonProcessingException {
        Cart cart = cartService.getCart();
        String phoneId = idAndQuanityPhone.getPhoneId();
        String quantity = idAndQuanityPhone.getQuantity();
        AddToCartPhone addToCartPhone = new AddToCartPhone(cart, quantity, Long.valueOf(phoneId));
        addToCartValidator.validate(addToCartPhone, result);
        if (result.hasErrors()) {
            String errorString = result.getAllErrors().get(0).getCode();
            return new ResponseEntity<>(errorString, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        } else {
            cartService.addPhone(Long.valueOf(phoneId), Integer.valueOf(quantity));
            String message = cart.getTotalCost().toString();
            return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.OK);
        }
    }
}
