package com.es.core.cart;

import com.es.core.phone.Color;
import com.es.core.phone.Phone;
import com.es.core.phone.Stock;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StockExtractor implements ResultSetExtractor<List<Stock>> {
    @Override
    public List<Stock> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<Stock> stockList = new ArrayList<>();
        while (resultSet.next()) {
            Stock stock = new Stock();
            Phone phone = new Phone();
            phone.setId(resultSet.getLong("id"));
            stock.setPhone(phone);
            if (stockList.contains(stock)) {
                stock = stockList.get(stockList.indexOf(stock));
                Color color = new Color(resultSet.getLong("colorId"), resultSet.getString("code"));
                Set<Color> colors = stock.getPhone().getColors();
                colors.add(color);
                stock.getPhone().setColors(colors);
            } else {
                addStockToStockList(stockList, stock, resultSet);
            }
        }
        return stockList;
    }

    private void addStockToStockList(List<Stock> stockList, Stock stock, ResultSet resultSet) throws SQLException {
        Long colorId = resultSet.getLong("colorId");
        Phone phone = stock.getPhone();
        String colorCode = resultSet.getString("code");
        phone.setBrand(resultSet.getString("brand"));
        phone.setModel(resultSet.getString("model"));
        phone.setPrice(resultSet.getBigDecimal("price"));
        phone.setDisplaySizeInches(resultSet.getBigDecimal("displaySizeInches"));
        phone.setWeightGr(resultSet.getInt("weightGr"));
        phone.setLengthMm(resultSet.getBigDecimal("lengthMm"));
        phone.setWidthMm(resultSet.getBigDecimal("widthMm"));
        phone.setHeightMm(resultSet.getBigDecimal("heightMm"));
        phone.setAnnounced(resultSet.getDate("announced"));
        phone.setDeviceType(resultSet.getString("deviceType"));
        phone.setOs(resultSet.getString("os"));
        phone.setDisplayResolution(resultSet.getString("displayResolution"));
        phone.setPixelDensity(resultSet.getInt("pixelDensity"));
        phone.setDisplayTechnology(resultSet.getString("displayTechnology"));
        phone.setBackCameraMegapixels(resultSet.getBigDecimal("backCameraMegapixels"));
        phone.setFrontCameraMegapixels(resultSet.getBigDecimal("frontCameraMegapixels"));
        phone.setRamGb(resultSet.getBigDecimal("ramGb"));
        phone.setInternalStorageGb(resultSet.getBigDecimal("internalStorageGb"));
        phone.setBatteryCapacityMah(resultSet.getInt("batteryCapacityMah"));
        phone.setTalkTimeHours(resultSet.getBigDecimal("talkTimeHours"));
        phone.setStandByTimeHours(resultSet.getBigDecimal("standByTimeHours"));
        phone.setBluetooth(resultSet.getString("bluetooth"));
        phone.setPositioning(resultSet.getString("positioning"));
        phone.setImageUrl(resultSet.getString("imageUrl"));
        phone.setDescription(resultSet.getString("description"));
        phone.setColors(new HashSet<>(Arrays.asList(new Color(colorId, colorCode))));
        stock.setStock(resultSet.getInt("stock"));
        stock.setPhone(phone);
        stockList.add(stock);
    }
}
