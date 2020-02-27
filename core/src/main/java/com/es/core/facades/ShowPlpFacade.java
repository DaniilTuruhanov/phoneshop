package com.es.core.facades;

import com.es.core.converters.FromPhoneToDataConverter;
import com.es.core.data.PlpData;
import com.es.core.models.Phone;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShowPlpFacade {
    @Resource
    private FromPhoneToDataConverter fromPhoneToDataConverter;

    public List<PlpData> findAll(List<Phone> phoneList) {
        List<PlpData> plpDataList = new ArrayList<>();
        for (Phone phone : phoneList) {
            PlpData plpData = (PlpData) fromPhoneToDataConverter.convert(phone);
            plpDataList.add(plpData);
        }
        return plpDataList;
    }
}
