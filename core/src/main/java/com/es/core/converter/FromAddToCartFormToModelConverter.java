package com.es.core.converter;

import com.es.core.form.AddToCartForm;
import com.es.core.model.AddToCartModel;
import com.es.core.populator.inter.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class FromAddToCartFormToModelConverter implements Converter<AddToCartForm, AddToCartModel> {

    private List<Populator> populators;

    @Override
    public AddToCartModel convert(AddToCartForm addToCartForm) {
        AddToCartModel addToCartModel = new AddToCartModel();
        for (Populator populator : populators) {
            populator.populate(addToCartForm, addToCartModel);
        }
        return addToCartModel;
    }

    public List<Populator> getPopulators() {
        return populators;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
