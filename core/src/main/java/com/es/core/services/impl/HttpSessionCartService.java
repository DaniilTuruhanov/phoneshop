package com.es.core.services.impl;

import com.es.core.models.AddToCartModel;
import com.es.core.models.CartEntity;
import com.es.core.exceptions.PhoneNotFoundException;
import com.es.core.models.Cart;
import com.es.core.models.Phone;
import org.springframework.stereotype.Service;
import com.es.core.services.interfaces.CartService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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
    public void addPhone(AddToCartModel addToCartModel) throws PhoneNotFoundException {
        Cart cart = getCart();
        Long phoneId = Long.valueOf(addToCartModel.getPhoneId());
        int quantity = Integer.parseInt(addToCartModel.getQuantity());
        Phone phone = phoneService.get(phoneId);
        CartEntity cartEntity= new CartEntity();
        cartEntity.setPhone(phone);
        cartEntity.setQuantity(quantity);
        List<CartEntity> cartEntityList = cart.getCartEntityList();
        if (cartEntityList.contains(cartEntity)) {
            int index = cartEntityList.indexOf(cartEntity);
            CartEntity cartEntityInList = cartEntityList.get(index);
            int stockReserved = cartEntityInList.getQuantity() + quantity;
            cartEntityList.get(index).setQuantity(stockReserved);
        } else {
            cartEntityList.add(cartEntity);
            cart.setPhoneStocks(cartEntityList);
        }
        recalculateTotals();
    }

    @Override
    public void update(Map<Long, Long> items) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void remove(Long phoneId) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public void recalculateTotals() {
        Cart cart = getCart();
        cart.setTotalCost(BigDecimal.valueOf(0));
        cart.setTotalQuantity(0);
        for (CartEntity stock : cart.getCartEntityList()) {
            cart.setTotalQuantity(cart.getTotalQuantity()+stock.getQuantity());
            BigDecimal multiply = stock.getPhone().getPrice().multiply(new BigDecimal(stock.getQuantity()));
            cart.setTotalCost(cart.getTotalCost().add(multiply));
        }
    }
}
