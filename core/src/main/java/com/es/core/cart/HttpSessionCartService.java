package com.es.core.cart;

import com.es.core.exceptions.PhoneNotFoundException;
import com.es.core.phone.Phone;
import com.es.core.phone.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class HttpSessionCartService implements CartService {

    @Autowired
    private StockService stockService;

    @Override
    public Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

    @Override
    public void addPhone(Cart cart, Long phoneId, Integer quantity) throws PhoneNotFoundException {
        Stock stock = stockService.get(phoneId);
        stock.setReserved(quantity);
        List<Stock> phoneStocks = cart.getPhoneStocks();
        if (phoneStocks.contains(stock)) {
            Stock stockInList = phoneStocks.get(phoneStocks.indexOf(stock));
            int stockReserved = (int) (stockInList.getReserved() + quantity);
            phoneStocks.get(phoneStocks.indexOf(stock)).setReserved(stockReserved);
        } else {
            phoneStocks.add(stock);
            cart.setPhoneStocks(phoneStocks);
        }
        cart.recalculateTotals();
    }

    @Override
    public void update(Map<Long, Long> items) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void remove(Long phoneId) {
        throw new UnsupportedOperationException("TODO");
    }
}
