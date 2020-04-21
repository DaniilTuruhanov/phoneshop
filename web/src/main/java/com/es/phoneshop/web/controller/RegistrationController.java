package com.es.phoneshop.web.controller;

import com.es.core.facade.RegistrationFacade;
import com.es.core.form.RegistrationUserForm;
import com.es.core.validator.RegistrationUserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Resource
    private RegistrationUserValidator registrationUserValidator;

    @Resource
    private RegistrationFacade registrationFacade;

    @GetMapping
    public String getRegisterPage() {
        return "registration";
    }

    @PostMapping
    public String saveUser(@ModelAttribute RegistrationUserForm userForm, Model model, BindingResult result) {
        registrationUserValidator.validate(userForm,result);
        if(result.hasErrors()){
            model.addAttribute("errors",result.getAllErrors());
            return "registration";
        }
        registrationFacade.saveUser(userForm.getUsername(), userForm.getFirstPassword());
        return "redirect:/products";
    }
}
