package com.es.core.populators.impl;

import com.es.core.forms.AddToCartForm;
import com.es.core.models.AddToCartModel;
import com.es.core.populators.interfaces.Populator;
import org.springframework.stereotype.Component;

@Component
public class FromAddToCartFormToModelPopulator implements Populator<AddToCartForm, AddToCartModel> {
    @Override
    public void populate(AddToCartForm addToCartForm, AddToCartModel addToCartModel) {
        addToCartModel.setPhoneId(addToCartForm.getPhoneId());
        addToCartModel.setQuantity(addToCartForm.getQuantity());
    }
}
