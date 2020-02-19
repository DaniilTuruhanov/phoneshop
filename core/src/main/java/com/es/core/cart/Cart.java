package com.es.core.cart;

import com.es.core.phone.Stock;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private BigDecimal totalCost;
    private Integer totalQuantity;
    private List<Stock> phoneStocks;

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public Cart() {
        totalCost = BigDecimal.valueOf(0);
        phoneStocks = new ArrayList<>();
        totalQuantity = 0;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public List<Stock> getPhoneStocks() {
        return phoneStocks;
    }

    public void setPhoneStocks(List<Stock> phoneStocks) {
        this.phoneStocks = phoneStocks;
    }

    public void recalculateTotals() {
        totalCost = BigDecimal.valueOf(0);
        totalQuantity = 0;
        for (Stock stock : phoneStocks) {
            totalQuantity += stock.getReserved();
            totalCost = totalCost.add(stock.getPhone().getPrice().multiply(new BigDecimal(stock.getReserved())));
        }
    }
}

