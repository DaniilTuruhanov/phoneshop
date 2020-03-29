package com.es.phoneshop.web.controller;

import com.es.core.exception.OrderNotFoundException;
import com.es.core.exception.PhoneNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
@EnableWebMvc
public class ExceptionHandlerController {

    @ExceptionHandler(PhoneNotFoundException.class)
    public String phoneNotFoundException(PhoneNotFoundException e, Model model) {
        model.addAttribute("id", e.getId());
        return "phoneNotFound";
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public String orderNotFoundException(OrderNotFoundException e, Model model){
        model.addAttribute("id", e.getId());
        return "orderNotFound";
    }
}
