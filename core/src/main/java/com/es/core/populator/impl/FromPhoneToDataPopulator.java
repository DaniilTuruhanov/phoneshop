package com.es.core.populator.impl;

import com.es.core.data.PlpData;
import com.es.core.model.Phone;
import com.es.core.populator.inter.Populator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FromPhoneToDataPopulator implements Populator<List<Phone>, PlpData> {

    @Override
    public void populate(List<Phone> phone, PlpData plpData) {
        plpData.setPhoneList(phone);
    }
}
