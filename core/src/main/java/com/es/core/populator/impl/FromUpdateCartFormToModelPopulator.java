package com.es.core.populator.impl;

import com.es.core.form.UpdateCartForm;
import com.es.core.model.UpdateCartModel;
import com.es.core.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class FromUpdateCartFormToModelPopulator implements Populator<UpdateCartForm, UpdateCartModel> {

    @Override
    public void populate(UpdateCartForm updateCartForm, UpdateCartModel updateCartModel) {
        for (int i = 0; i < updateCartForm.getPhonesId().size(); i++) {
            updateCartModel.getPhonesId().add(Long.valueOf(updateCartForm.getPhonesId().get(i).trim()));
            updateCartModel.getQuantity().add(Integer.valueOf(updateCartForm.getQuantity().get(i)));
        }
    }
}
