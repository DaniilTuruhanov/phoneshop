package com.es.core.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class ProductService {
    @Autowired
    private PhoneDao phoneDao;


    public Phone get(final Long key) {
        Optional<Phone> phone = phoneDao.get(key);
        if (phone.isPresent()) {
            return phone.get();
        }
        return phone.get();
    }

    public void save(final Phone phone) {
        phoneDao.save(phone);
    }

    public List<Phone> findAll(int offset, int limit) {
        return phoneDao.findAll(offset, limit);
    }
}