package com.es.core.phone;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/resources/context/testApplicationContextCore.xml")

public class JdbcPhoneDaoTest {

    @Autowired
    private JdbcPhoneDao phoneDao;

    private static JdbcTemplate jdbcTemplate;

    @Before
    public void injectJdbcTemplate() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("resources/context/testApplicationContextCore.xml");
        jdbcTemplate = applicationContext.getBean("jdbcTemplate", JdbcTemplate.class);
    }

    @AfterClass
    public static void destroyTables() {
        jdbcTemplate.update("drop table if exists phone2color");
        jdbcTemplate.update("drop table if exists phones");
        jdbcTemplate.update("drop table if exists colors");
    }

    @Test
    public void returnPhoneListWhenSomeProductsExistInDb() {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        phone1.setId(1000L);
        phone1.setBrand("ARCHOS");
        phone1.setModel("ARCHOS 101 G9");
        phone1.setColors(new HashSet<>(Collections.singletonList(new Color(1000L, "Black"))));
        phone2.setId(1001L);
        phone2.setBrand("ARCHOS");
        phone2.setModel("ARCHOS 101 Helium 4G");
        phone2.setColors(new HashSet<>(Collections.singletonList(new Color(1001L, "White"))));

        List<Phone> expectedPhoneList = new ArrayList<>();
        expectedPhoneList.add(phone1);
        expectedPhoneList.add(phone2);

        List<Phone> actualPhoneList = phoneDao.findAll(0, 10);
        for (int i = 0; i < actualPhoneList.size(); i++) {
            assertEquals(expectedPhoneList.get(i).getId(), actualPhoneList.get(i).getId());
            assertEquals(expectedPhoneList.get(i).getBrand(), actualPhoneList.get(i).getBrand());
            assertEquals(expectedPhoneList.get(i).getModel(), actualPhoneList.get(i).getModel());
            assertEquals(expectedPhoneList.get(i).getColors(), actualPhoneList.get(i).getColors());
        }
    }

    @Test
    public void returnPhoneWhenIdExistInDb() {
        Phone expectedPhone = new Phone();

        expectedPhone.setId(1000L);
        expectedPhone.setBrand("ARCHOS");
        expectedPhone.setModel("ARCHOS 101 G9");
        expectedPhone.setColors(new HashSet<>(Collections.singletonList(new Color(1000L, "Black"))));

        Optional<Phone> actualPhone = phoneDao.get(1000L);

        assertEquals(expectedPhone.getId(), actualPhone.get().getId());
        assertEquals(expectedPhone.getBrand(), actualPhone.get().getBrand());
        assertEquals(expectedPhone.getModel(), actualPhone.get().getModel());
        assertEquals(expectedPhone.getColors(), actualPhone.get().getColors());
    }

    @Test
    public void returnEmptyOptionalWhenIdDoesNotExistInDb() {
        Optional<Phone> actualPhone = phoneDao.get(1L);
        assertFalse(actualPhone.isPresent());
    }

    @Test
    public void savePhoneWhenPhoneDoesNotExistInDb() {
        Phone expectedPhone = new Phone();

        expectedPhone.setId(2L);
        expectedPhone.setBrand("ARC");
        expectedPhone.setModel("ARCHOS");
        expectedPhone.setColors(new HashSet<>(Collections.singletonList(new Color(1000L, "Black"))));

        phoneDao.save(expectedPhone);

        Optional<Phone> actualPhone = phoneDao.get(expectedPhone.getId());
        assertEquals(expectedPhone.getId(), actualPhone.get().getId());
        assertEquals(expectedPhone.getBrand(), actualPhone.get().getBrand());
        assertEquals(expectedPhone.getModel(), actualPhone.get().getModel());
        assertEquals(expectedPhone.getColors(), actualPhone.get().getColors());
    }

    @Test
    public void replacePhoneWhenPhoneExistInDb() {
        Phone expectedPhone = new Phone();

        expectedPhone.setId(1000L);
        expectedPhone.setBrand("Nokia");
        expectedPhone.setModel("5110");
        expectedPhone.setColors(new HashSet<>(Collections.singletonList(new Color(1001L, "White"))));

        phoneDao.save(expectedPhone);

        Optional<Phone> actualPhone = phoneDao.get(expectedPhone.getId());
        assertEquals(expectedPhone.getId(), actualPhone.get().getId());
        assertEquals(expectedPhone.getBrand(), actualPhone.get().getBrand());
        assertEquals(expectedPhone.getModel(), actualPhone.get().getModel());
        assertEquals(expectedPhone.getColors(), actualPhone.get().getColors());
    }
}