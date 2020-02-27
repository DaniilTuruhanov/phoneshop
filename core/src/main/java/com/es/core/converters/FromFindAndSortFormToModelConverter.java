package com.es.core.converters;

import com.es.core.forms.FindAndSortForm;
import com.es.core.models.FindAndSortModel;
import com.es.core.populators.impl.FromFindAndSortFormToModelPopulator;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class FromFindAndSortFormToModelConverter implements Converter<FindAndSortForm, FindAndSortModel> {

    @Resource
    private FromFindAndSortFormToModelPopulator populatorFromFindAndSortFormToModel;

    @Override
    public FindAndSortModel convert(FindAndSortForm findAndSortForm) {
        FindAndSortModel findAndSortModel = new FindAndSortModel();
        populatorFromFindAndSortFormToModel.populate(findAndSortForm, findAndSortModel);
        return findAndSortModel;
    }
}
