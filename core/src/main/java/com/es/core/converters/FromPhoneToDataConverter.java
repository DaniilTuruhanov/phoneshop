package com.es.core.converters;

import com.es.core.data.PlpData;
import com.es.core.models.Phone;
import com.es.core.populators.interfaces.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class FromPhoneToDataConverter implements Converter<Phone, PlpData> {

    private List<Populator> populatorList;

    @Override
    public PlpData convert(Phone phone) {
        PlpData plpData = new PlpData();
        for (Populator populator : populatorList) {
            populator.populate(phone, plpData);
        }
        return plpData;
    }

    public List<Populator> getPopulatorList() {
        return populatorList;
    }

    public void setPopulatorList(List<Populator> populatorList) {
        this.populatorList = populatorList;
    }
}
