package com.es.core.converter;

import com.es.core.data.PlpData;
import com.es.core.model.Phone;
import com.es.core.populator.inter.Populator;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class FromPhoneToDataConverter implements Converter<List<Phone>, PlpData> {

    private List<Populator> populators;

    @Override
    public PlpData convert(List<Phone> phone) {
        PlpData plpData = new PlpData();
        for (Populator populator : populators) {
            populator.populate(phone, plpData);
        }
        return plpData;
    }

    public List<Populator> getPopulators() {
        return populators;
    }

    public void setPopulators(List<Populator> populators) {
        this.populators = populators;
    }
}
