package com.es.core.dao;

import com.es.core.model.RegistrationUser;

import java.util.Optional;

public interface UserDao {

    void save(String username, String password);

    Optional<RegistrationUser> get(String username);

}
