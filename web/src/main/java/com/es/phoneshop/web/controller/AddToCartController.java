package com.es.phoneshop.web.controller;

import com.es.core.exception.PhoneNotFoundException;
import com.es.core.facade.CartFacade;
import com.es.core.form.AddToCartForm;
import com.es.core.validator.AddToCartValidator;
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
@RequestMapping(value = "/products/add", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddToCartController {

    @Resource
    private CartFacade cartFacade;

    @Resource
    private AddToCartValidator addToCartValidator;

    @PostMapping
    public ResponseEntity addPhone(@ModelAttribute AddToCartForm addToCartForm, BindingResult result) throws PhoneNotFoundException, JsonProcessingException {
        addToCartValidator.validate(addToCartForm, result);
        if (result.hasErrors()) {
            String errorString = result.getAllErrors().get(0).getCode();
            return new ResponseEntity<>(errorString, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        } else {
            cartFacade.addToCart(addToCartForm);
            String message = cartFacade.getTotalCost().toString();
            return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.OK);
        }
    }
}
