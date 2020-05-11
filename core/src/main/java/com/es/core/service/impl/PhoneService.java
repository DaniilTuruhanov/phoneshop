package com.es.core.service.impl;

import com.es.core.dao.PhoneDao;
import com.es.core.exception.PhoneNotFoundException;
import com.es.core.model.FindAndSortModel;
import com.es.core.model.Phone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
@PropertySource("classpath:application.properties")
public class PhoneService {

    @Value("${limit}")
    private int limit;

    @Resource
    private PhoneDao phoneDao;

    public Phone get(final Long key) throws PhoneNotFoundException {
        Optional<Phone> phone = phoneDao.get(key);
        return phone.orElseThrow(() -> new PhoneNotFoundException(key.toString()));
    }

    public Phone get(final String key) throws PhoneNotFoundException {
        Optional<Phone> phone = phoneDao.get(key);
        return phone.orElseThrow(() -> new PhoneNotFoundException(key.toString()));
    }

    public void save(final Phone phone) {
        phoneDao.save(phone);
    }

    public List<Phone> findAll(FindAndSortModel findAndSortModel) {
        findAndSortModel.setLimit(limit);
        List<Phone> phoneList = phoneDao.findAll(findAndSortModel);
        return phoneList;
    }

    public int countPage(FindAndSortModel findAndSortModel) {
        findAndSortModel.setLimit(limit);
        int countPage = phoneDao.countPage(findAndSortModel);
        return countPage;
    }
}