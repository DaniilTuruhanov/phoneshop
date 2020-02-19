package com.es.core.cart;

import com.es.core.exceptions.PhoneNotFoundException;
import com.es.core.phone.Phone;
import com.es.core.phone.PhoneDao;
import com.es.core.phone.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StockService {

    @Autowired
    private StockDao stockDao;

    public Stock get(final Long key) throws PhoneNotFoundException {
        Optional<Stock> stock = stockDao.get(key);
        return stock.orElseThrow(PhoneNotFoundException::new);
    }
}