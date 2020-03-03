package com.es.core.facade;

import com.es.core.converter.FromPhoneToDataConverter;
import com.es.core.data.PlpData;
import com.es.core.model.Phone;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ShowPlpFacade {
    @Resource
    private FromPhoneToDataConverter fromPhoneToDataConverter;

    public PlpData findAll(List<Phone> phoneList) {
        PlpData plpData = fromPhoneToDataConverter.convert(phoneList);
        return plpData;
    }
}
