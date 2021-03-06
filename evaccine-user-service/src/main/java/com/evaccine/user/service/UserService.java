package com.evaccine.user.service;

import java.util.List;

import com.evaccine.user.model.UserRegisterRequest;
import com.evaccine.user.model.UserRegisterResponse;
import com.evaccine.user.model.UserVaccineInfoResponse;
import com.evaccine.user.model.VaccineRegisterRequest;

public interface UserService {

    public UserRegisterResponse registerUserDetails(final UserRegisterRequest userDetailsRequest);

    public UserRegisterResponse getUserDetails(final String id);

    public UserRegisterResponse updateUserDetails(final UserRegisterRequest userDetailsRequest);

    public UserRegisterResponse vaccineRegisterRequest(final VaccineRegisterRequest vaccineRegisterRequest);

    List<UserVaccineInfoResponse> fetchVaccineInfo(final String countryCode, final String pincode);


}
