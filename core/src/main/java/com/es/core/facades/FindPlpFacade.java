package com.es.core.facades;

import com.es.core.converters.FromFindAndSortFormToModelConverter;
import com.es.core.data.PlpData;
import com.es.core.forms.FindAndSortForm;
import com.es.core.models.FindAndSortModel;
import com.es.core.models.Phone;
import com.es.core.services.impl.PhoneService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class FindPlpFacade {

    @Resource
    private FromFindAndSortFormToModelConverter converterFromFindAndSortFormToModel;

    @Resource
    private ShowPlpFacade showPlpFacade;

    @Resource
    private PhoneService phoneService;

    public List<PlpData> findAll(FindAndSortForm findAndSortForm) {
        FindAndSortModel findAndSortModel = converterFromFindAndSortFormToModel.convert(findAndSortForm);
        return showPlpFacade.findAll(phoneService.findAll(findAndSortModel));
    }

    public int countPage(FindAndSortForm findAndSortForm) {
        FindAndSortModel findAndSortModel = converterFromFindAndSortFormToModel.convert(findAndSortForm);
        return phoneService.countPage(findAndSortModel);
    }

}
