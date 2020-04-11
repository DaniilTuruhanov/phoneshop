package com.es.core.validator;

import com.es.core.form.UserInfoForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserInfoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserInfoForm.class.equals(aClass);
    }

    @Override
    public void validate(Object validateObject, Errors errors) {
        UserInfoForm userForm = (UserInfoForm) validateObject;
        if (userForm.getFirstName() == null || userForm.getFirstName().trim().equals("") || Pattern.matches("(.*[0-9]+.*)", userForm.getFirstName())) {
            errors.reject("First name", "First name is required");
        }
        if (userForm.getLastName() == null || userForm.getLastName().trim().equals("") || Pattern.matches("(.*[0-9]+.*)", userForm.getLastName())) {
            errors.reject("Last name", "Last name is required");
        }

        if (userForm.getPhone() == null || userForm.getPhone().trim().equals("") || Pattern.matches("(.*[a-zA-Zа-яА-Я]+.*)", userForm.getPhone())) {
            errors.reject("Phone", "Phone is required");
        }

        if (userForm.getAddress() == null || userForm.getAddress().trim().equals("")) {
            errors.reject("Address", "Address is required");
        }
    }
}
