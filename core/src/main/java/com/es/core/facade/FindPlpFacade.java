package com.es.core.facade;

import com.es.core.converter.FromFindAndSortFormToModelConverter;
import com.es.core.data.PlpData;
import com.es.core.form.FindAndSortForm;
import com.es.core.model.FindAndSortModel;
import com.es.core.service.impl.PhoneService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class FindPlpFacade {

    @Resource
    private FromFindAndSortFormToModelConverter converterFromFindAndSortFormToModel;

    @Resource
    private ShowPlpFacade showPlpFacade;

    @Resource
    private PhoneService phoneService;

    public PlpData findAll(FindAndSortForm findAndSortForm) {
        FindAndSortModel findAndSortModel = converterFromFindAndSortFormToModel.convert(findAndSortForm);
        return showPlpFacade.findAll(phoneService.findAll(findAndSortModel));
    }

    public int countPage(FindAndSortForm findAndSortForm) {
        FindAndSortModel findAndSortModel = converterFromFindAndSortFormToModel.convert(findAndSortForm);
        return phoneService.countPage(findAndSortModel);
    }

}
