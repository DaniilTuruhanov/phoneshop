package com.es.phoneshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @RequestMapping
    public String getLoginPage(@RequestParam(defaultValue = "") String error, Model model) {
        return "login";
    }
}
