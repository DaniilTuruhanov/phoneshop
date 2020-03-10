package com.es.core.populator.impl;

import com.es.core.data.PhonePlpData;
import com.es.core.model.Phone;
import com.es.core.populator.inter.Populator;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FromPhoneListToDataPopulator implements Populator<List<Phone>, List<PhonePlpData>> {

    @Override
    public void populate(List<Phone> phones, List<PhonePlpData> phonePlpDataList) {
        for (Phone phone : phones) {
            PhonePlpData phonePlpData = new PhonePlpData();
            phonePlpData.setId(phone.getId());
            phonePlpData.setBrand(phone.getBrand());
            phonePlpData.setColors(phone.getColors());
            phonePlpData.setDisplaySizeInches(phone.getDisplaySizeInches());
            phonePlpData.setImageUrl(phone.getImageUrl());
            phonePlpData.setModel(phone.getModel());
            phonePlpData.setPrice(phone.getPrice());
            phonePlpDataList.add(phonePlpData);
        }
    }
}
