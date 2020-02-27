package com.es.core.converters;

import com.es.core.forms.AddToCartForm;
import com.es.core.models.AddToCartModel;
import com.es.core.populators.impl.FromAddToCartFormToModelPopulator;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class FromAddToCartFormToModelConverter implements Converter<AddToCartForm, AddToCartModel> {

    @Resource
    private FromAddToCartFormToModelPopulator fromAddToCartFormToModelPopulator;

    @Override
    public AddToCartModel convert(AddToCartForm addToCartForm) {
        AddToCartModel addToCartModel = new AddToCartModel();
        fromAddToCartFormToModelPopulator.populate(addToCartForm, addToCartModel);
        return addToCartModel;
    }
}
