package com.es.core.service.impl;

import com.es.core.dao.UserDao;
import com.es.core.model.RegistrationUser;
import com.es.core.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class RegistrationUserService implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(String username, String password) {
        String encodePassword = bCryptPasswordEncoder.encode(password);
        userDao.save(username, encodePassword);
    }

    @Override
    public Optional<RegistrationUser> get(String username) {
        return userDao.get(username);
    }
}