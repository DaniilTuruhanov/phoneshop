package com.es.core.populators.impl;

import com.es.core.forms.FindAndSortForm;
import com.es.core.models.FindAndSortModel;
import com.es.core.populators.interfaces.Populator;

public class FromFindAndSortFormToModelPopulator implements Populator<FindAndSortForm, FindAndSortModel> {

    @Override
    public void populate(FindAndSortForm findAndSortForm, FindAndSortModel findAndSortModel) {
        findAndSortModel.setPage(findAndSortForm.getPage());
        findAndSortModel.setOrder(findAndSortForm.getOrder());
        findAndSortModel.setQuery(findAndSortForm.getQuery());
        findAndSortModel.setSort(findAndSortForm.getSort());
    }
}
