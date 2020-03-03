package com.es.core.populator.impl;

import com.es.core.form.FindAndSortForm;
import com.es.core.model.FindAndSortModel;
import com.es.core.populator.inter.Populator;
import org.springframework.stereotype.Component;

@Component
public class FromFindAndSortFormToModelPopulator implements Populator<FindAndSortForm, FindAndSortModel> {

    @Override
    public void populate(FindAndSortForm findAndSortForm, FindAndSortModel findAndSortModel) {
        findAndSortForm.setPage(findAndSortForm.getPage().equals("") ? "0" : findAndSortForm.getPage());
        findAndSortModel.setPage(findAndSortForm.getPage());
        findAndSortModel.setOrder(findAndSortForm.getOrder());
        findAndSortModel.setQuery(findAndSortForm.getQuery());
        findAndSortModel.setSort(findAndSortForm.getSort());
    }
}
