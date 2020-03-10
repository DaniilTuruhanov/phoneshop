package com.es.core.facade;

import com.es.core.converter.FromFindAndSortFormToModelConverter;
import com.es.core.converter.FromPhoneListToDataConverter;
import com.es.core.data.PhonePlpData;
import com.es.core.form.FindAndSortForm;
import com.es.core.model.FindAndSortModel;
import com.es.core.service.impl.PhoneService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class PlpFacade {

    @Resource
    private FromFindAndSortFormToModelConverter converterFromFindAndSortFormToModel;

    @Resource
    private FromPhoneListToDataConverter fromPhoneListToDataConverter;

    @Resource
    private PhoneService phoneService;

    public List<PhonePlpData> findAll(FindAndSortForm findAndSortForm) {
        FindAndSortModel findAndSortModel = converterFromFindAndSortFormToModel.convert(findAndSortForm);
        return fromPhoneListToDataConverter.convert(phoneService.findAll(findAndSortModel));
    }

    public int countPage(FindAndSortForm findAndSortForm) {
        FindAndSortModel findAndSortModel = converterFromFindAndSortFormToModel.convert(findAndSortForm);
        return phoneService.countPage(findAndSortModel);
    }

}
