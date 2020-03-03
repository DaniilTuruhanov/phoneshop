package com.es.core.converter;

import com.es.core.form.FindAndSortForm;
import com.es.core.model.FindAndSortModel;
import com.es.core.populator.inter.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class FromFindAndSortFormToModelConverter implements Converter<FindAndSortForm, FindAndSortModel> {

    private List<Populator> populators;

    @Override
    public FindAndSortModel convert(FindAndSortForm findAndSortForm) {
        FindAndSortModel findAndSortModel = new FindAndSortModel();
        for (Populator populator : populators) {
            populator.populate(findAndSortForm, findAndSortModel);
        }
        return findAndSortModel;
    }

    public List<Populator> getPopulators() {
        return populators;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
