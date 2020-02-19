package com.es.core.cart;

import com.es.core.cart.StockDao;
import com.es.core.phone.Color;
import com.es.core.phone.Phone;
import com.es.core.phone.PhoneDao;
import com.es.core.phone.PhoneExtractor;
import com.es.core.phone.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class JdbcStockDao implements StockDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_STOCK_QUERY = "select * from (select * from phones inner join stocks s on id=s.phoneId where s.stock>0 and id=?) p left outer join phone2color p2с on p.id=p2с.phoneId left outer join colors c on p2с.colorId=c.id";

    public Optional<Stock> get(final Long key) {
        List<Stock> stockList = jdbcTemplate.query(GET_STOCK_QUERY, new StockExtractor(), key);
        return stockList.stream().findAny();
    }
}