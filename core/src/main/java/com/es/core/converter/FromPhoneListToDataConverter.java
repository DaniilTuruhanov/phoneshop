package com.es.core.converter;

import com.es.core.data.PhonePlpData;
import com.es.core.model.Phone;
import com.es.core.populator.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class FromPhoneListToDataConverter implements Converter<List<Phone>, List<PhonePlpData>> {

    private List<Populator> populators;

    @Override
    public List<PhonePlpData> convert(List<Phone> phone) {
        List<PhonePlpData> phonePlpData = new ArrayList<>();
        for (Populator populator : populators) {
            populator.populate(phone, phonePlpData);
        }
        return phonePlpData;
    }

    public List<Populator> getPopulators() {
        return populators;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
