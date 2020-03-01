package com.es.core.converters;

import com.es.core.forms.AddToCartForm;
import com.es.core.models.AddToCartModel;
import com.es.core.populators.interfaces.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class FromAddToCartFormToModelConverter implements Converter<AddToCartForm, AddToCartModel> {

    private List<Populator> populatorList;

    @Override
    public AddToCartModel convert(AddToCartForm addToCartForm) {
        AddToCartModel addToCartModel = new AddToCartModel();
        for (Populator populator : populatorList) {
            populator.populate(addToCartForm, addToCartModel);
        }
        return addToCartModel;
    }

    public List<Populator> getPopulatorList() {
        return populatorList;
    }

    public void setPopulatorList(List<Populator> populatorList) {
        this.populatorList = populatorList;
    }
}
