package com.es.core.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class JdbcPhoneDao implements PhoneDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String GET_PHONE_QUERY = "select * from (select * from phones where id=?) p inner join phone2color p2с on p.id=p2с.phoneId inner join colors c on p2с.colorId=c.id";
    private static final String UPDATE_PHONES_QUERY = "update phones set brand=?,model=?,price=?,displaySizeInches=?,weightGr=?,lengthMm=?,widthMm=?,heightMm=?,announced=?,deviceType=?,os=?,displayResolution=?,pixelDensity=?,displayTechnology=?,backCameraMegapixels=?,frontCameraMegapixels=?,ramGb=?,internalStorageGb=?,batteryCapacityMah=?,talkTimeHours=?,standByTimeHours=?,bluetooth=?,positioning=?,imageUrl=?,description=? where id=?";
    private static final String DELETE_FROM_PHONE2COLOR_QUERY = "delete from phone2color where phoneId = ? and colorId = ?";
    private static final String INSERT_INTO_PHONES_QUERY = "insert into phones values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERT_INTO_PHONE2COLOR_QUERY = "insert into phone2color (phoneId, colorId) values (?,?)";
    private static final String GET_PHONES_QUERY = "select * from (select * from phones inner join stocks s on id=s.phoneId where s.stock>0 offset ? limit ?) p left outer join phone2color p2с on p.id=p2с.phoneId left outer join colors c on p2с.colorId=c.id";
    private static final String GET_COUNT_ROW_QUERY = "select count(*) from phones inner join stocks s on id=s.phoneId where s.stock>0";
    private static final String GET_COLORS_QUERY = "select c.id, c.code from phone2color p2c inner join colors c on c.id=p2c.colorId where phoneId=?";

    public Optional<Phone> get(final Long key) {
        List<Phone> phoneList = jdbcTemplate.query(GET_PHONE_QUERY, new PhoneExtractor(), key);
        for (Phone phone : phoneList) {
            if (phone.getPrice() == null) {
                phone.setPrice(BigDecimal.valueOf(1));
            }
        }
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
        List<Color> colorsFromPhone2Color = jdbcTemplate.query(GET_COLORS_QUERY, new BeanPropertyRowMapper(Color.class), id);
        for (Color color : colorsFromPhone) {
            if (!colorsFromPhone2Color.contains(color)) {
                Long colorId = color.getId();
                jdbcTemplate.update(INSERT_INTO_PHONE2COLOR_QUERY, id, colorId);
            }
        }
        for (Color color : colorsFromPhone2Color) {
            if (!colorsFromPhone.contains(color)) {
                Long colorId = color.getId();
                jdbcTemplate.update(DELETE_FROM_PHONE2COLOR_QUERY, id, colorId);
            }
        }
    }

    public List<Phone> findAll(int offset, int limit, String query, String order, String sort) {
        if (query.length() > 0) {
            List<String> queryList = queryParams(query);
            String findInBrand = likeIn("brand", queryList);
            String findInDescription = likeIn("description", queryList);
            String getPhonesWithQuery = "select * from (select * from phones inner join stocks s on id=s.phoneId where s.stock>0 and " + findInBrand + " or " + findInDescription + " offset ? limit ?) p left outer join phone2color p2с on p.id=p2с.phoneId left outer join colors c on p2с.colorId=c.id";
            if (order.length() > 0) {
                getPhonesWithQuery = "select * from (select * from phones inner join stocks s on id=s.phoneId where s.stock>0 and " + findInBrand + " or " + findInDescription + " order by " + order + " " + sort + " offset ? limit ?) p left outer join phone2color p2с on p.id=p2с.phoneId left outer join colors c on p2с.colorId=c.id ";
                return jdbcTemplate.query(getPhonesWithQuery, new PhoneExtractor(), offset, limit);
            }
            return jdbcTemplate.query(getPhonesWithQuery, new PhoneExtractor(), offset, limit);
        } else {
            if (order.length() > 0) {
                String getPhonesWithQuery = "select * from (select * from phones inner join stocks s on id=s.phoneId where s.stock>0 order by " + order + " " + sort + " offset ? limit ?) p left outer join phone2color p2с on p.id=p2с.phoneId left outer join colors c on p2с.colorId=c.id";
                return jdbcTemplate.query(getPhonesWithQuery, new PhoneExtractor(), offset, limit);
            }
            return findAll(offset, limit);
        }
    }

    @Override
    public int countPage(int limit, String query, String order, String sort) {
        int count;
        if (query.length() > 0) {
            List<String> queryList = queryParams(query);
            String findInBrand = likeIn("brand", queryList);
            String findInDescription = likeIn("description", queryList);
            String getCountRows = "select count(*) from phones inner join stocks s on id=s.phoneId where s.stock>0 and " + findInBrand + " or " + findInDescription;
            if (order.length() > 0) {
                getCountRows = "select count(*) from phones inner join stocks s on id=s.phoneId where s.stock>0 and " + findInBrand + " or " + findInDescription + " order by " + order + " " + sort;
                count = jdbcTemplate.queryForObject(getCountRows, Integer.class);
                return (int) Math.ceil((double) count / limit);
            }
            count = jdbcTemplate.queryForObject(getCountRows, Integer.class);
            return (int) Math.ceil((double) count / limit);
        } else {
            if (order.length() > 0) {
                String getCountRows = "select count(*) from phones inner join stocks s on id=s.phoneId where s.stock>0 order by " + order + " " + sort;
                count = jdbcTemplate.queryForObject(getCountRows, Integer.class);
                return (int) Math.ceil((double) count / limit);
            }
            return countPage(limit);
        }
    }

    public int countPage(int limit) {
        int count = jdbcTemplate.queryForObject(GET_COUNT_ROW_QUERY, Integer.class);
        return (int) Math.ceil((double) count / limit);
    }

    public List<Phone> findAll(int offset, int limit) {
        return jdbcTemplate.query(GET_PHONES_QUERY, new PhoneExtractor(), offset, limit);
    }

    private List<String> queryParams(String query) {
        List<String> queryParams = Arrays.asList(query.trim().split(" "));
        return queryParams;
    }

    private String likeIn(String name, List<String> queryParams) {
        String likeIn = "";
        for (int i = 0; i < queryParams.size() - 1; i++) {
            likeIn += name + " like '%" + queryParams.get(i) + "%' or ";
        }
        likeIn += name + " like '%" + queryParams.get(queryParams.size() - 1) + "%'";
        return likeIn;
    }


    private void updatePhone(Phone phone) {
        jdbcTemplate.update(UPDATE_PHONES_QUERY, phone.getBrand(), phone.getModel(), phone.getPrice(), phone.getDisplaySizeInches(), phone.getWeightGr(), phone.getLengthMm(), phone.getWidthMm(), phone.getHeightMm(), phone.getAnnounced(), phone.getDeviceType(), phone.getOs(), phone.getDisplayResolution(), phone.getPixelDensity(), phone.getDisplayTechnology(), phone.getBackCameraMegapixels(), phone.getFrontCameraMegapixels(), phone.getRamGb(), phone.getInternalStorageGb(), phone.getBatteryCapacityMah(), phone.getTalkTimeHours(), phone.getStandByTimeHours(), phone.getBluetooth(), phone.getPositioning(), phone.getImageUrl(), phone.getDescription(), phone.getId());
    }

    private void addPhone(Phone phone) {
        jdbcTemplate.update(INSERT_INTO_PHONES_QUERY, phone.getId(), phone.getBrand(), phone.getModel(), phone.getPrice(), phone.getDisplaySizeInches(), phone.getWeightGr(), phone.getLengthMm(), phone.getWidthMm(), phone.getHeightMm(), phone.getAnnounced(), phone.getDeviceType(), phone.getOs(), phone.getDisplayResolution(), phone.getPixelDensity(), phone.getDisplayTechnology(), phone.getBackCameraMegapixels(), phone.getFrontCameraMegapixels(), phone.getRamGb(), phone.getInternalStorageGb(), phone.getBatteryCapacityMah(), phone.getTalkTimeHours(), phone.getStandByTimeHours(), phone.getBluetooth(), phone.getPositioning(), phone.getImageUrl(), phone.getDescription());
    }
}