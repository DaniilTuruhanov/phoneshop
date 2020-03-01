package com.es.core.converters;

import com.es.core.forms.FindAndSortForm;
import com.es.core.models.FindAndSortModel;
import com.es.core.populators.interfaces.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class FromFindAndSortFormToModelConverter implements Converter<FindAndSortForm, FindAndSortModel> {

    private List<Populator> populatorList;

    @Override
    public FindAndSortModel convert(FindAndSortForm findAndSortForm) {
        findAndSortForm.setPage(findAndSortForm.getPage().equals("") ? "0" : findAndSortForm.getPage());
        FindAndSortModel findAndSortModel = new FindAndSortModel();
        for (Populator populator : populatorList) {
            populator.populate(findAndSortForm, findAndSortModel);
        }
        return findAndSortModel;
    }

    public List<Populator> getPopulatorList() {
        return populatorList;
    }

    public void setPopulatorList(List<Populator> populatorList) {
        this.populatorList = populatorList;
    }
}
