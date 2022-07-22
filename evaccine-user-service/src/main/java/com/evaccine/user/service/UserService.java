package com.evaccine.user.service;

import com.evaccine.user.model.UserRegisterRequest;
import com.evaccine.user.model.UserRegisterResponse;

public interface UserService {

    public UserRegisterResponse registerUserDetails(final UserRegisterRequest userDetailsRequest);

    public UserRegisterResponse getUserDetails(final String id);

    public UserRegisterResponse updateUserDetails(final UserRegisterRequest userDetailsRequest);
}
