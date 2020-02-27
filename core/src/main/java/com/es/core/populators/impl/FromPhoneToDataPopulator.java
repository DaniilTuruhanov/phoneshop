package com.es.core.populators.impl;

import com.es.core.data.PlpData;
import com.es.core.models.Phone;
import com.es.core.populators.interfaces.Populator;
import org.springframework.stereotype.Component;

@Component
public class FromPhoneToDataPopulator implements Populator<Phone, PlpData> {

    @Override
    public void populate(Phone phone, PlpData plpData) {
        plpData.setBrand(phone.getBrand());
        plpData.setColors(phone.getColors());
        plpData.setDisplaySizeInches(phone.getDisplaySizeInches());
        plpData.setId(phone.getId());
        plpData.setImageUrl(phone.getImageUrl());
        plpData.setPrice(phone.getPrice());
        plpData.setModel(phone.getModel());
    }
}
