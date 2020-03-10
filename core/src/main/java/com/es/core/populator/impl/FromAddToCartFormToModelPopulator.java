package com.es.core.populator.impl;

import com.es.core.form.AddToCartForm;
import com.es.core.model.AddToCartModel;
import com.es.core.populator.inter.Populator;
import org.springframework.stereotype.Component;

@Component
public class FromAddToCartFormToModelPopulator implements Populator<AddToCartForm, AddToCartModel> {

    @Override
    public void populate(AddToCartForm addToCartForm, AddToCartModel addToCartModel) {
        addToCartModel.setPhoneId(Long.valueOf(addToCartForm.getPhoneId()));
        addToCartModel.setQuantity(Integer.valueOf(addToCartForm.getQuantity()));
    }
}
