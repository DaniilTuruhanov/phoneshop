package com.es.core.phone;

import com.es.core.exceptions.PhoneNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
@PropertySource("classpath:application.properties")
public class PhoneService {

    @Value("${limit}")
    private int limit;

    @Autowired
    private PhoneDao phoneDao;

    public Phone get(final Long key) throws PhoneNotFoundException {
        Optional<Phone> phone = phoneDao.get(key);
        return phone.orElseThrow(PhoneNotFoundException::new);
    }

    public void save(final Phone phone) {
        phoneDao.save(phone);
    }

    public List<Phone> findAll(int offset, String query, String order, String sort) {
        List<Phone> phoneList = phoneDao.findAll(offset * limit, limit, query, order, sort);

        for (Phone phone : phoneList) {
            if (phone.getPrice() == null) {
                phone.setPrice(BigDecimal.valueOf(1));
            }
        }
        return phoneList;
    }

    public int countPage(String query, String order, String sort) {
        int countPage = phoneDao.countPage(limit, query, order, sort);
        return countPage;
    }


}