package com.es.core.validator;

import com.es.core.form.RegistrationUserForm;
import com.es.core.form.UserInfoForm;
import com.es.core.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;

@Component
public class RegistrationUserValidator implements Validator {

    @Resource
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserInfoForm.class.equals(aClass);
    }

    @Override
    public void validate(Object validateObject, Errors errors) {
        RegistrationUserForm registrationUserForm = (RegistrationUserForm) validateObject;
        if (userService.get(registrationUserForm.getUsername()).isPresent()) {
            errors.reject("username", "Sorry, but user with username " + registrationUserForm.getUsername() + " is already exist");
        } else {
            if (!registrationUserForm.getFirstPassword().equals(registrationUserForm.getSecondPassword())) {
                errors.reject("password", "Passwords are not equals");
            } else {
                if (registrationUserForm.getFirstPassword().length() < 8) {
                    errors.reject("password", "Min length of password  = 8");
                }
            }
        }
    }
}