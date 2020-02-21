package com.es.core.cart;

import com.es.core.exceptions.PhoneNotFoundException;
import com.es.core.phone.Phone;
import com.es.core.phone.PhoneService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class HttpSessionCartService implements CartService {

    @Resource
    private PhoneService phoneService;

    @Resource
    private HttpSession session;

    @Override
    public Cart getCart() {
        Cart cart = (Cart) session.getAttribute("cart");
        return cart;
    }

    @Override
    public void addPhone(Long phoneId, Integer quantity) throws PhoneNotFoundException {
        Cart cart = getCart();
        Phone phone = phoneService.get(phoneId);
        CartEntity cartEntity= new CartEntity();
        cartEntity.setPhone(phone);
        cartEntity.setReserved(quantity);
        List<CartEntity> cartEntityList = cart.getCartEntityList();
        if (cartEntityList.contains(cartEntity)) {
            int index = cartEntityList.indexOf(cartEntity);
            CartEntity cartEntityInList = cartEntityList.get(index);
            int stockReserved = cartEntityInList.getReserved() + quantity;
            cartEntityList.get(index).setReserved(stockReserved);
        } else {
            cartEntityList.add(cartEntity);
            cart.setPhoneStocks(cartEntityList);
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
