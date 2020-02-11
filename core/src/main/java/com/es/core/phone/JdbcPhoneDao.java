package com.es.core.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class JdbcPhoneDao implements PhoneDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Phone> get(final Long key) {
        final String getPhone = "select * from (select * from phones phoneTable where phoneTable.id=?) p inner join phone2color p2с on p.id=p2с.phoneId inner join colors c on p2с.colorId=c.id";
        List<Phone> phoneList = jdbcTemplate.query(getPhone, new PhoneExtractor(), key);
        return phoneList.stream().findAny();
    }

    public void save(final Phone phone) {
        final String updatePhones = "update phones set brand=?,model=?,price=?,displaySizeInches=?,weightGr=?,lengthMm=?,widthMm=?,heightMm=?,announced=?,deviceType=?,os=?,displayResolution=?,pixelDensity=?,displayTechnology=?,backCameraMegapixels=?,frontCameraMegapixels=?,ramGb=?,internalStorageGb=?,batteryCapacityMah=?,talkTimeHours=?,standByTimeHours=?,bluetooth=?,positioning=?,imageUrl=?,description=? where id=?";
        final String deleteFrom2Color = "delete from phone2color where phoneId = ?";
        final String insertIntoPhones = "insert into phones values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        final String insertIntoPhone2Color = "insert into phone2color (phoneId, colorId) values (?,?)";
        Optional<Phone> optionalPhone = get(phone.getId());

        if (optionalPhone.isPresent()) {
            jdbcTemplate.update(updatePhones, phone.getBrand(), phone.getModel(), phone.getPrice(), phone.getDisplaySizeInches(), phone.getWeightGr(), phone.getLengthMm(), phone.getWidthMm(), phone.getHeightMm(), phone.getAnnounced(), phone.getDeviceType(), phone.getOs(), phone.getDisplayResolution(), phone.getPixelDensity(), phone.getDisplayTechnology(), phone.getBackCameraMegapixels(), phone.getFrontCameraMegapixels(), phone.getRamGb(), phone.getInternalStorageGb(), phone.getBatteryCapacityMah(), phone.getTalkTimeHours(), phone.getStandByTimeHours(), phone.getBluetooth(), phone.getPositioning(), phone.getImageUrl(), phone.getDescription(), phone.getId());
            jdbcTemplate.update(deleteFrom2Color, phone.getId());
        } else {
            jdbcTemplate.update(insertIntoPhones, phone.getId(), phone.getBrand(), phone.getModel(), phone.getPrice(), phone.getDisplaySizeInches(), phone.getWeightGr(), phone.getLengthMm(), phone.getWidthMm(), phone.getHeightMm(), phone.getAnnounced(), phone.getDeviceType(), phone.getOs(), phone.getDisplayResolution(), phone.getPixelDensity(), phone.getDisplayTechnology(), phone.getBackCameraMegapixels(), phone.getFrontCameraMegapixels(), phone.getRamGb(), phone.getInternalStorageGb(), phone.getBatteryCapacityMah(), phone.getTalkTimeHours(), phone.getStandByTimeHours(), phone.getBluetooth(), phone.getPositioning(), phone.getImageUrl(), phone.getDescription());
        }
        Set<Color> colors = phone.getColors();
        for (Color color : colors) {
            jdbcTemplate.update(insertIntoPhone2Color, phone.getId(), color.getId());
        }
    }

    public List<Phone> findAll(int offset, int limit) {
        List<Phone> phoneList;
        final String getPhones = "select * from (select * from phones offset " + offset + " limit " + limit + ") p inner join phone2color p2с on p.id=p2с.phoneId inner join colors c on p2с.colorId=c.id";
        phoneList = jdbcTemplate.query(getPhones, new PhoneExtractor());
        return phoneList;
    }

}