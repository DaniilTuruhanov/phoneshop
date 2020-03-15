package com.es.core.service.impl;

import com.es.core.exception.PhoneNotFoundException;
import com.es.core.model.AddToCartModel;
import com.es.core.model.CartModel;
import com.es.core.model.CartEntity;
import com.es.core.model.Phone;
import com.es.core.model.UpdateCartModel;
import com.es.core.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Service
public class HttpSessionCartService implements CartService {

    @Resource
    private PhoneService phoneService;

    @Resource
    private HttpSession session;

    @Override
    public CartModel getCart() {
        CartModel cart = (CartModel) session.getAttribute("cart");
        return cart;
    }

    @Override
    public void addPhone(AddToCartModel addToCartModel) throws PhoneNotFoundException {
        CartModel cart = getCart();
        Long phoneId = addToCartModel.getPhoneId();
        int quantity = addToCartModel.getQuantity();
        Phone phone = phoneService.get(phoneId);
        CartEntity cartEntity = new CartEntity();
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
            cart.setCartEntityList(cartEntityList);
        }
        recalculateTotals();
    }

    @Override
    public void update(UpdateCartModel updateCartModel) {
        CartModel cart = getCart();
        Phone phone = new Phone();
        for (int i = 0; i < updateCartModel.getPhonesId().size(); i++) {
            CartEntity cartEntity = new CartEntity();
            phone.setId(updateCartModel.getPhonesId().get(i));
            cartEntity.setPhone(phone);
            int index = cart.getCartEntityList().indexOf(cartEntity);
            cart.getCartEntityList().get(index).setQuantity(updateCartModel.getQuantity().get(i));
        }
        recalculateTotals();
    }

    @Override
    public void remove(Long phoneId) throws PhoneNotFoundException {
        CartModel cart = getCart();
        Phone phone = phoneService.get(phoneId);
        CartEntity cartEntity = new CartEntity();
        cartEntity.setPhone(phone);
        List<CartEntity> cartEntityList = cart.getCartEntityList();
        if (cartEntityList.contains(cartEntity)) {
            cartEntityList.remove(cartEntity);
            cart.setCartEntityList(cartEntityList);
        }
        recalculateTotals();
    }

    @Override
    public void cleanCart() {
        session.setAttribute("cart", new CartModel());
    }

    @Override
    public void recalculateTotals() {
        CartModel cart = getCart();
        cart.setTotalCost(BigDecimal.valueOf(0));
        cart.setTotalQuantity(0);
        for (CartEntity stock : cart.getCartEntityList()) {
            cart.setTotalQuantity(cart.getTotalQuantity() + stock.getQuantity());
            BigDecimal multiply = stock.getPhone().getPrice().multiply(new BigDecimal(stock.getQuantity()));
            cart.setTotalCost(cart.getTotalCost().add(multiply));
        }
    }
}
