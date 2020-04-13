package com.es.core.dao.impl;

import com.es.core.dao.UserDao;
import com.es.core.model.RegistrationUser;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class JdbcUserDao implements UserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_INTO_USERS_QUERY = "insert into users values (?,?,?)";

    private static final String INSERT_INTO_USER2ROLE_QUERY = "insert into user2role values (?,?)";

    private static final String GET_USER_WITH_USERNAME_QUERY = "select username, password from users where username = ?";

    @Override
    public void save(String username, String password) {
        jdbcTemplate.update(INSERT_INTO_USERS_QUERY, username, password, true);
        jdbcTemplate.update(INSERT_INTO_USER2ROLE_QUERY, username, 1);
    }

    @Override
    public Optional<RegistrationUser> get(String username) {
        List<RegistrationUser> users = jdbcTemplate.query(GET_USER_WITH_USERNAME_QUERY, new BeanPropertyRowMapper(RegistrationUser.class), username);
        return users.stream().findAny();
    }
}