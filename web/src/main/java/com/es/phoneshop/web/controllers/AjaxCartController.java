package com.es.phoneshop.web.controllers;

import com.es.core.facades.CartFacade;
import com.es.core.models.Cart;
import com.es.core.services.interfaces.CartService;
import com.es.core.exceptions.PhoneNotFoundException;
import com.es.core.forms.AddToCartForm;
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
    private CartFacade cartFacade;

    @Resource
    private AddToCartValidator addToCartValidator;

    @PostMapping
    public ResponseEntity addPhone(@ModelAttribute AddToCartForm addToCartForm, BindingResult result) throws PhoneNotFoundException, JsonProcessingException {
        Cart cart = cartFacade.getCart();
        addToCartValidator.validate(addToCartForm, result);
        if (result.hasErrors()) {
            String errorString = result.getAllErrors().get(0).getCode();
            return new ResponseEntity<>(errorString, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        } else {
            cartFacade.addToCart(addToCartForm);
            String message = cart.getTotalCost().toString();
            return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.OK);
        }
    }
}
