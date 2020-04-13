package com.es.core.facade;

import com.es.core.service.impl.RegistrationUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RegistrationFacade {

    @Resource
    private RegistrationUserService registrationUserService;

    public void saveUser(String username, String password) {
        registrationUserService.save(username, password);
    }
}
