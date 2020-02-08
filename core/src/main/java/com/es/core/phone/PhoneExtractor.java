package com.es.core.phone;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PhoneExtractor implements ResultSetExtractor<List<Phone>> {
    @Override
    public List<Phone> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<Phone> phoneList = new ArrayList<>();
        Long prevId = 0L;
        Long nextId = 1L;
        while (resultSet.next()) {
            nextId = resultSet.getLong("phoneId");
            if (prevId.equals(nextId)) {
                Phone phone = phoneList.get(phoneList.size() - 1);
                Color color = new Color(resultSet.getLong("colorId"), resultSet.getString("code"));
                Set<Color> colors = phone.getColors();
                colors.add(color);
                phone.setColors(colors);
            } else {
                Phone phone = new Phone();
                Long colorId = resultSet.getLong("colorId");
                String colorCode = resultSet.getString("code");
                phone.setBrand(resultSet.getString("brand"));
                phone.setId(resultSet.getLong("phoneId"));
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
                phoneList.add(phone);
            }
            prevId = resultSet.getLong("phoneId");
        }
        return phoneList;
    }
}
