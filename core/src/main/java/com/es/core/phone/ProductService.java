package com.es.core.phone;

import com.es.core.exceptions.PhoneNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductService {
    @Autowired
    private PhoneDao phoneDao;


    public Phone get(final Long key) throws PhoneNotFoundException {
        Optional<Phone> phone = phoneDao.get(key);
        if (phone.isPresent()) {
            return phone.get();
        } else throw new PhoneNotFoundException();
    }

    public void save(final Phone phone) {
        phoneDao.save(phone);
    }

    public List<Phone> findAll(int offset, int limit) {
        return phoneDao.findAll(offset, limit);
    }
}