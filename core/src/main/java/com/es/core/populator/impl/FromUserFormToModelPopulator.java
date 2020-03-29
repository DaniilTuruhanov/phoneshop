package com.es.core.populator.impl;

import com.es.core.form.UserInfoForm;
import com.es.core.model.UserModel;
import com.es.core.populator.Populator;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FromUserFormToModelPopulator implements Populator<UserInfoForm, UserModel> {

    @Override
    public void populate(UserInfoForm userForm, UserModel userModel) {
        userModel.setAddress(userForm.getAddress());
        userModel.setDate(new Date());
        userModel.setDescription(userForm.getDescription());
        userModel.setFirstName(userForm.getFirstName());
        userModel.setLastName(userForm.getLastName());
        userModel.setPhone(userForm.getPhone());
    }
}
