package com.es.core.facade;

import com.es.core.converter.FromPhoneToDataConverter;
import com.es.core.data.PhoneData;
import com.es.core.data.PhoneDetailsData;
import com.es.core.exception.PhoneNotFoundException;
import com.es.core.model.Phone;
import com.es.core.service.impl.PhoneService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PhoneFacade {

    @Resource
    private PhoneService phoneService;

    @Resource
    private FromPhoneToDataConverter fromPhoneToDataConverter;

    public PhoneDetailsData getPhone(String phoneId) throws PhoneNotFoundException {
        Phone phone;
        try {
            Long id = Long.valueOf(phoneId);
            phone = phoneService.get(id);
        } catch (NumberFormatException e) {
            throw new PhoneNotFoundException(phoneId);
        }
        return fromPhoneToDataConverter.convert(phone);
    }
}
