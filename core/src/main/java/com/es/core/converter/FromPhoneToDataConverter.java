package com.es.core.converter;

import com.es.core.data.PhoneData;
import com.es.core.data.PhoneDetailsData;
import com.es.core.model.Phone;
import com.es.core.populator.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class FromPhoneToDataConverter implements Converter<Phone, PhoneData> {

    private List<Populator> populators;

    @Override
    public PhoneDetailsData convert(Phone phone) {
        PhoneDetailsData phoneDetailsData = new PhoneDetailsData();
        for (Populator populator : populators) {
            populator.populate(phone, phoneDetailsData);
        }
        return phoneDetailsData;
    }

    public List<Populator> getPopulators() {
        return populators;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
