package com.es.core.phone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:resources/context/testApplicationContextWeb.xml")
@WebAppConfiguration
public class ProductListPageControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void injectJdbcTemplate() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void returnPhoneListWhenSomeProductsExistInDb() throws Exception {
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

        mockMvc.perform(get("/productList"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("phones", expectedPhoneList))
                .andExpect(view().name("productList"));
    }
}

