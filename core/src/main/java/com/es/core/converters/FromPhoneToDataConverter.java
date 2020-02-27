package com.es.core.converters;

import com.es.core.data.PlpData;
import com.es.core.models.Phone;
import com.es.core.populators.impl.FromPhoneToDataPopulator;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class FromPhoneToDataConverter implements Converter<Phone, PlpData> {

    @Resource
    private FromPhoneToDataPopulator populatorFromPhoneToData;

    @Override
    public PlpData convert(Phone phone) {
        PlpData plpData = new PlpData();
        populatorFromPhoneToData.populate(phone, plpData);
        return plpData;
    }
}
