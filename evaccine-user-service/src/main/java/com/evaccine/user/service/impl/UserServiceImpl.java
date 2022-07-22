package com.evaccine.user.service.impl;

import com.evaccine.user.model.UserRegisterRequest;
import com.evaccine.user.model.UserRegisterResponse;
import com.evaccine.user.service.UserService;
import com.evaccine.user.validator.UserServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserServiceValidator userServiceValidator;
    @Override
    public UserRegisterResponse registerUserDetails(final UserRegisterRequest userDetailsRequest) {

        //validate UserRegisterRequest
        userServiceValidator.validateUserDetails(userDetailsRequest);
        return null;
    }

    @Override
    public UserRegisterResponse getUserDetails(final String id) {
        return null;
    }

    @Override
    public UserRegisterResponse updateUserDetails(final UserRegisterRequest userDetailsRequest) {
        return null;
    }
}
