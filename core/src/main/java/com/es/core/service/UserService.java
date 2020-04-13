package com.es.core.service;

import com.es.core.model.RegistrationUser;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface UserService {
    void save(String username, String password) throws NoSuchAlgorithmException;

    Optional<RegistrationUser> get(String username);
}
