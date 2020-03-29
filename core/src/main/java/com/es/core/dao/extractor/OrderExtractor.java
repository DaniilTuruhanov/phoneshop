package com.es.core.dao.extractor;

import com.es.core.model.CartEntity;
import com.es.core.model.Color;
import com.es.core.model.OrderModel;
import com.es.core.model.Phone;
import com.es.core.model.UserModel;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class OrderExtractor implements ResultSetExtractor<List<OrderModel>> {

    @Override
    public List<OrderModel> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<OrderModel> orderList = new ArrayList<>();
        while (resultSet.next()) {
            OrderModel order = new OrderModel();
            CartEntity cartEntity = new CartEntity();
            cartEntity.setPhone(new Phone());
            cartEntity.getPhone().setId(resultSet.getLong("phoneId"));
            order.setId(resultSet.getString("orderId"));
            if (orderList.contains(order)) {
                order = orderList.get(orderList.indexOf(order));
                if (order.getCartEntityList().contains(cartEntity)) {
                    cartEntity = order.getCartEntityList().get(order.getCartEntityList().indexOf(cartEntity));
                    Color color = new Color(resultSet.getLong("colorId"), resultSet.getString("code"));
                    cartEntity.getPhone().getColors().add(color);
                } else {
                    addCartEntityToOrder(order.getCartEntityList(), resultSet);
                }
            } else addOrderToList(orderList, order, resultSet);
        }
        for (OrderModel order : orderList) {
            recalculate(order);
        }
        return orderList;
    }

    private void addCartEntityToOrder(List<CartEntity> cartEntityList, ResultSet resultSet) throws SQLException {
        Long colorId = resultSet.getLong("colorId");
        String colorCode = resultSet.getString("code");
        CartEntity cartEntity = new CartEntity();
        cartEntity.setPhone(new Phone());
        cartEntity.getPhone().setId(resultSet.getLong("phoneId"));
        cartEntity.getPhone().setBrand(resultSet.getString("brand"));
        cartEntity.getPhone().setModel(resultSet.getString("model"));
        cartEntity.getPhone().setPrice(resultSet.getBigDecimal("price"));
        cartEntity.getPhone().setDisplaySizeInches(resultSet.getBigDecimal("displaySizeInches"));
        cartEntity.getPhone().setWeightGr(resultSet.getInt("weightGr"));
        cartEntity.getPhone().setLengthMm(resultSet.getBigDecimal("lengthMm"));
        cartEntity.getPhone().setWidthMm(resultSet.getBigDecimal("widthMm"));
        cartEntity.getPhone().setHeightMm(resultSet.getBigDecimal("heightMm"));
        cartEntity.getPhone().setAnnounced(resultSet.getDate("announced"));
        cartEntity.getPhone().setDeviceType(resultSet.getString("deviceType"));
        cartEntity.getPhone().setOs(resultSet.getString("os"));
        cartEntity.getPhone().setDisplayResolution(resultSet.getString("displayResolution"));
        cartEntity.getPhone().setPixelDensity(resultSet.getInt("pixelDensity"));
        cartEntity.getPhone().setDisplayTechnology(resultSet.getString("displayTechnology"));
        cartEntity.getPhone().setBackCameraMegapixels(resultSet.getBigDecimal("backCameraMegapixels"));
        cartEntity.getPhone().setFrontCameraMegapixels(resultSet.getBigDecimal("frontCameraMegapixels"));
        cartEntity.getPhone().setRamGb(resultSet.getBigDecimal("ramGb"));
        cartEntity.getPhone().setInternalStorageGb(resultSet.getBigDecimal("internalStorageGb"));
        cartEntity.getPhone().setBatteryCapacityMah(resultSet.getInt("batteryCapacityMah"));
        cartEntity.getPhone().setTalkTimeHours(resultSet.getBigDecimal("talkTimeHours"));
        cartEntity.getPhone().setStandByTimeHours(resultSet.getBigDecimal("standByTimeHours"));
        cartEntity.getPhone().setBluetooth(resultSet.getString("bluetooth"));
        cartEntity.getPhone().setPositioning(resultSet.getString("positioning"));
        cartEntity.getPhone().setImageUrl(resultSet.getString("imageUrl"));
        cartEntity.getPhone().setDescription(resultSet.getString("description"));
        cartEntity.getPhone().setStock(resultSet.getInt("stock"));
        cartEntity.getPhone().setColors(new HashSet<>(Arrays.asList(new Color(colorId, colorCode))));
        cartEntity.setQuantity(resultSet.getInt("quantity"));
        cartEntityList.add(cartEntity);
    }

    private void addOrderToList(List<OrderModel> orderList, OrderModel order, ResultSet resultSet) throws SQLException {
        order.setUserModel(new UserModel());
        order.getUserModel().setFirstName(resultSet.getString("firstName"));
        order.getUserModel().setLastName(resultSet.getString("lastName"));
        order.getUserModel().setAddress(resultSet.getString("address"));
        order.getUserModel().setPhone(resultSet.getString("phone"));
        order.getUserModel().setDescription(resultSet.getString("orderDescription"));
        order.getUserModel().setDate(resultSet.getDate("data"));
        order.setId(resultSet.getString("orderId"));
        order.setNumber(resultSet.getInt("number"));
        order.setStatus(resultSet.getString("status"));
        order.setDeliveryCost(resultSet.getBigDecimal("delivery"));
        order.setSubtotalCost(new BigDecimal("0"));
        addCartEntityToOrder(order.getCartEntityList(), resultSet);
        orderList.add(order);
    }

    private void recalculate(OrderModel order) {
        for (CartEntity cartEntity : order.getCartEntityList()) {
            order.setSubtotalCost(order.getSubtotalCost().add(cartEntity.getPhone().getPrice().multiply(new BigDecimal(cartEntity.getQuantity()))));
        }
        order.setTotalCost(order.getSubtotalCost().add(order.getDeliveryCost()));
    }
}