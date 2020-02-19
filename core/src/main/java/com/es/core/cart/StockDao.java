package com.es.core.cart;

import com.es.core.phone.Phone;
import com.es.core.phone.Stock;

import java.util.List;
import java.util.Optional;

public interface StockDao {
    Optional<Stock> get(Long key);
}
