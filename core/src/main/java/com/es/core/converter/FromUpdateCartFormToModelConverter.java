package com.es.core.converter;

import com.es.core.form.UpdateCartForm;
import com.es.core.model.UpdateCartModel;
import com.es.core.populator.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class FromUpdateCartFormToModelConverter implements Converter<UpdateCartForm, UpdateCartModel> {

    private List<Populator> populators;

    @Override
    public UpdateCartModel convert(UpdateCartForm updateCartForm) {
        UpdateCartModel updateCartModel = new UpdateCartModel();
        for (Populator populator : populators) {
            populator.populate(updateCartForm, updateCartModel);
        }
        return updateCartModel;
    }

    public List<Populator> getPopulators() {
        return populators;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
