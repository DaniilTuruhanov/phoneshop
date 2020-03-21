package com.es.core.populator.impl;

import com.es.core.data.PhoneDetailsData;
import com.es.core.model.Phone;
import com.es.core.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class FromPhoneToDataPopulator implements Populator<Phone, PhoneDetailsData> {

    @Override
    public void populate(Phone phone, PhoneDetailsData phoneData) {
        phoneData.setId(phone.getId());
        phoneData.setModel(phone.getModel());
        phoneData.setPrice(phone.getPrice());
        phoneData.setDisplaySizeInches(phone.getDisplaySizeInches());
        phoneData.setWeightGr(phone.getWeightGr());
        phoneData.setLengthMm(phone.getLengthMm());
        phoneData.setWidthMm(phone.getWidthMm());
        phoneData.setDeviceType(phone.getDeviceType());
        phoneData.setOs(phone.getOs());
        phoneData.setColors(phone.getColors());
        phoneData.setDisplayResolution(phone.getDisplayResolution());
        phoneData.setPixelDensity(phone.getPixelDensity());
        phoneData.setDisplayTechnology(phone.getDisplayTechnology());
        phoneData.setBackCameraMegapixels(phone.getBackCameraMegapixels());
        phoneData.setFrontCameraMegapixels(phone.getFrontCameraMegapixels());
        phoneData.setBatteryCapacityMah(phone.getBatteryCapacityMah());
        phoneData.setTalkTimeHours(phone.getTalkTimeHours());
        phoneData.setStandByTimeHours(phone.getStandByTimeHours());
        phoneData.setBluetooth(phone.getBluetooth());
        phoneData.setImageUrl(phone.getImageUrl());
        phoneData.setDescription(phone.getDescription());
    }
}
