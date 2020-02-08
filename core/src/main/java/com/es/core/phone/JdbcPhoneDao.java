package com.es.core.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class JdbcPhoneDao implements PhoneDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Phone> get(final Long key) {
        List<Phone> phoneList;
        phoneList = jdbcTemplate.query("select * from (select * from phones phoneTable where phoneTable.id=" + key + ") p inner join phone2color p2с on p.id=p2с.phoneId inner join colors c on p2с.colorId=c.id", new PhoneExtractor());
        return phoneList.stream().findAny();
    }

    public void save(final Phone phone) {
        List<Phone> phoneList;
        phoneList = jdbcTemplate.query("select * from phones p where p.id = " + phone.getId(), new BeanPropertyRowMapper(Phone.class));
        if (!phoneList.isEmpty()) {
            jdbcTemplate.update("delete from phones where id = ?", phone.getId());
            jdbcTemplate.update("delete from phone2color where phoneId = ?", phone.getId());
        }
        Set<Color> colors = phone.getColors();
        jdbcTemplate.update("insert into phones values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", phone.getId(), phone.getBrand(), phone.getModel(), phone.getPrice(), phone.getDisplaySizeInches(), phone.getWeightGr(), phone.getLengthMm(), phone.getWidthMm(), phone.getHeightMm(), phone.getAnnounced(), phone.getDeviceType(), phone.getOs(), phone.getDisplayResolution(), phone.getPixelDensity(), phone.getDisplayTechnology(), phone.getBackCameraMegapixels(), phone.getFrontCameraMegapixels(), phone.getRamGb(), phone.getInternalStorageGb(), phone.getBatteryCapacityMah(), phone.getTalkTimeHours(), phone.getStandByTimeHours(), phone.getBluetooth(), phone.getPositioning(), phone.getImageUrl(), phone.getDescription());
        for (Color color : colors) {
            List<Color> colorsList = jdbcTemplate.query("select * from colors where id=" + color.getId(), new BeanPropertyRowMapper(Color.class));
            if (colorsList.isEmpty()) {
                jdbcTemplate.update("insert into colors (id, code) values (?,?)", color.getId(), color.getCode());
            } else {
                jdbcTemplate.update("update colors set code = ? where id =? ", color.getCode(), color.getId());
            }
            jdbcTemplate.update("insert into phone2color (phoneId, colorId) values (?,?)", phone.getId(), color.getId());
        }
    }

    public List<Phone> findAll(int offset, int limit) {
        List<Phone> phoneList = new ArrayList<>();
        phoneList = jdbcTemplate.query("select * from (select * from phones offset " + offset + " limit " + limit + ") p inner join phone2color p2с on p.id=p2с.phoneId inner join colors c on p2с.colorId=c.id", new PhoneExtractor());
        return phoneList;
    }

}