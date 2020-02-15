package com.es.core.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class JdbcPhoneDao implements PhoneDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String getPhone = "select * from (select * from phones where id=?) p inner join phone2color p2с on p.id=p2с.phoneId inner join colors c on p2с.colorId=c.id";
    private static final String updatePhones = "update phones set brand=?,model=?,price=?,displaySizeInches=?,weightGr=?,lengthMm=?,widthMm=?,heightMm=?,announced=?,deviceType=?,os=?,displayResolution=?,pixelDensity=?,displayTechnology=?,backCameraMegapixels=?,frontCameraMegapixels=?,ramGb=?,internalStorageGb=?,batteryCapacityMah=?,talkTimeHours=?,standByTimeHours=?,bluetooth=?,positioning=?,imageUrl=?,description=? where id=?";
    private static final String deleteFromPhone2Color = "delete from phone2color where phoneId = ? and colorId = ?";
    private static final String insertIntoPhones = "insert into phones values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String insertIntoPhone2Color = "insert into phone2color (phoneId, colorId) values (?,?)";
    private static final String getPhones = "select * from (select * from phones offset ? limit ?) p inner join phone2color p2с on p.id=p2с.phoneId inner join colors c on p2с.colorId=c.id";
    private static final String getColors = "select c.id, c.code from phone2color p2c inner join colors c on c.id=p2c.colorId where phoneId=?";

    public Optional<Phone> get(final Long key) {
        List<Phone> phoneList = jdbcTemplate.query(getPhone, new PhoneExtractor(), key);
        return phoneList.stream().findAny();
    }

    public void save(final Phone phone) {
        Long id = phone.getId();
        Optional<Phone> optionalPhone = get(id);

        if (optionalPhone.isPresent()) {
            updatePhone(phone);
        } else {
            addPhone(phone);
        }
        Set<Color> colorsFromPhone = phone.getColors();
        List<Color> colorsFromPhone2Color = jdbcTemplate.query(getColors, new BeanPropertyRowMapper(Color.class), id);
        for (Color color : colorsFromPhone) {
            if (!colorsFromPhone2Color.contains(color)) {
                Long colorId = color.getId();
                jdbcTemplate.update(insertIntoPhone2Color, id, colorId);
            }
        }
        for (Color color : colorsFromPhone2Color) {
            if (!colorsFromPhone.contains(color)) {
                Long colorId = color.getId();
                jdbcTemplate.update(deleteFromPhone2Color, id, colorId);
            }
        }
    }

    public List<Phone> findAll(int offset, int limit) {
        return jdbcTemplate.query(getPhones, new PhoneExtractor(), offset, limit);
    }

    private void updatePhone(Phone phone) {
        jdbcTemplate.update(updatePhones, phone.getBrand(), phone.getModel(), phone.getPrice(), phone.getDisplaySizeInches(), phone.getWeightGr(), phone.getLengthMm(), phone.getWidthMm(), phone.getHeightMm(), phone.getAnnounced(), phone.getDeviceType(), phone.getOs(), phone.getDisplayResolution(), phone.getPixelDensity(), phone.getDisplayTechnology(), phone.getBackCameraMegapixels(), phone.getFrontCameraMegapixels(), phone.getRamGb(), phone.getInternalStorageGb(), phone.getBatteryCapacityMah(), phone.getTalkTimeHours(), phone.getStandByTimeHours(), phone.getBluetooth(), phone.getPositioning(), phone.getImageUrl(), phone.getDescription(), phone.getId());
    }

    private void addPhone(Phone phone) {
        jdbcTemplate.update(insertIntoPhones, phone.getId(), phone.getBrand(), phone.getModel(), phone.getPrice(), phone.getDisplaySizeInches(), phone.getWeightGr(), phone.getLengthMm(), phone.getWidthMm(), phone.getHeightMm(), phone.getAnnounced(), phone.getDeviceType(), phone.getOs(), phone.getDisplayResolution(), phone.getPixelDensity(), phone.getDisplayTechnology(), phone.getBackCameraMegapixels(), phone.getFrontCameraMegapixels(), phone.getRamGb(), phone.getInternalStorageGb(), phone.getBatteryCapacityMah(), phone.getTalkTimeHours(), phone.getStandByTimeHours(), phone.getBluetooth(), phone.getPositioning(), phone.getImageUrl(), phone.getDescription());
    }
}