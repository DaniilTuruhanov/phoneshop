package com.es.core.converter;

import com.es.core.form.UserInfoForm;
import com.es.core.model.UserModel;
import com.es.core.populator.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class FromUserFormToModelConverter implements Converter<UserInfoForm, UserModel> {

    private List<Populator> populators;

    @Override
    public UserModel convert(UserInfoForm userForm) {
        UserModel userModel = new UserModel();
        for (Populator populator : populators) {
            populator.populate(userForm, userModel);
        }
        return userModel;
    }

    public List<Populator> getPopulators() {
        return populators;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }


}
