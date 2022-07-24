package com.evaccine.user.service.impl;

import static com.evaccine.user.constants.UserServiceConstants.COUNTRY_CODE_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.FETCH_USER_DETAILS_FAILED;
import static com.evaccine.user.constants.UserServiceConstants.FETCH_USER_DETAILS_SUCCESS_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.PIN_CODE_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.USER;
import static com.evaccine.user.constants.UserServiceConstants.USER_DETAILS_REGISTER_SUCCESS_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.USER_DETAILS_UPDATE_SUCCESS_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.USER_REGISTER_FOR_VACCINATION_SUCCESS_MESSAGE;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.evaccine.user.entity.UserEntity;
import com.evaccine.user.entity.VaccineInfoEntity;
import com.evaccine.user.model.UserRegisterRequest;
import com.evaccine.user.model.UserRegisterResponse;
import com.evaccine.user.model.UserVaccineInfoResponse;
import com.evaccine.user.model.VaccineRegisterRequest;
import com.evaccine.user.repository.UserEntityRepository;
import com.evaccine.user.repository.VaccineInfoRepository;
import com.evaccine.user.service.UserService;
import com.evaccine.user.validator.UserServiceValidator;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserServiceValidator userServiceValidator;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private VaccineInfoRepository vaccineInfoRepository;

    @Override
    public UserRegisterResponse registerUserDetails(final UserRegisterRequest userDetailsRequest) {

        //validate UserRegisterRequest
        userServiceValidator.validateUserDetails(userDetailsRequest);
        saveOrUpdateUserDetails(userDetailsRequest);
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setHttpStatus(HttpStatus.OK);
        userRegisterResponse.setMessage(USER_DETAILS_REGISTER_SUCCESS_MESSAGE);
        return userRegisterResponse;
    }

    @Override
    public UserRegisterResponse getUserDetails(final String id) {

        UserEntity userEntity = userEntityRepository.findByAadharNumber(id);
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        if (null != userEntity) {
            log.info("userDetails exists for given AadharNumber : {}", id);
            BeanUtils.copyProperties(userEntity, userRegisterResponse);
            userRegisterResponse.setMessage(FETCH_USER_DETAILS_SUCCESS_MESSAGE);
        } else {
            log.info("No userDetails exists for given AadharNumber : {}", id);
            userRegisterResponse.setMessage(FETCH_USER_DETAILS_FAILED + id);
            userRegisterResponse.setHttpStatus(HttpStatus.OK);
        }
        return userRegisterResponse;

    }

    @Override
    public UserRegisterResponse updateUserDetails(final UserRegisterRequest userDetailsRequest) {
        //validate UserRegisterRequest
        userServiceValidator.validateUserDetails(userDetailsRequest);
        //update existing aadhar details with given userDetailsRequest
        saveOrUpdateUserDetails(userDetailsRequest);
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setHttpStatus(HttpStatus.OK);
        userRegisterResponse.setMessage(USER_DETAILS_UPDATE_SUCCESS_MESSAGE);
        return userRegisterResponse;
    }

    @Override
    public UserRegisterResponse vaccineRegisterRequest(final VaccineRegisterRequest vaccineRegisterRequest) {

        userServiceValidator.validateVaccineRegisterRequest(vaccineRegisterRequest);
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setHttpStatus(HttpStatus.OK);
        userRegisterResponse.setMessage(USER_REGISTER_FOR_VACCINATION_SUCCESS_MESSAGE);
        return userRegisterResponse;
    }

    @Override
    public List<UserVaccineInfoResponse> fetchVaccineInfo(final String countryCode, final String pincode) {
        log.info("fetchVaccineInfo Request Received for :{}", countryCode);
        Assert.isTrue(StringUtils.isNotBlank(countryCode), COUNTRY_CODE_MANDATORY);
        Assert.isTrue(StringUtils.isNotBlank(pincode), PIN_CODE_MANDATORY);

        List<VaccineInfoEntity> vaccineInfoEntityList = vaccineInfoRepository
                .findByHospitalPincodeAndCountryCode(pincode, countryCode);

        List<UserVaccineInfoResponse> vaccineInfoResponseList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(vaccineInfoEntityList)) {
            for (VaccineInfoEntity vaccineInfoEntity : vaccineInfoEntityList) {
                UserVaccineInfoResponse vaccineInfoResponse = new UserVaccineInfoResponse();
                BeanUtils.copyProperties(vaccineInfoEntity, vaccineInfoResponse);
                vaccineInfoResponseList.add(vaccineInfoResponse);
            }
        } else {
            log.info("No Details found for given countryCode {} and pincode {}", countryCode, pincode);
        }
        return vaccineInfoResponseList;
    }


    private void saveOrUpdateUserDetails(final UserRegisterRequest userDetailsRequest) {

        UserEntity userEntity = userEntityRepository.findByAadharNumber(userDetailsRequest.getAadharNumber());
        if (null != userEntity) {
            log.info("userDetails exists for given AadharNumber : {}", userDetailsRequest.getAadharNumber());
        } else {
            log.info("No userDetails exists for given AadharNumber : {}.Creating a new entry into DB ",
                    userDetailsRequest.getAadharNumber());
            userEntity = new UserEntity();
            userEntity.setCreatedBy(USER);
        }
        BeanUtils.copyProperties(userDetailsRequest, userEntity);
        userEntity.setUpdatedBy(USER);
        userEntity.setUpdatedDate(LocalDateTime.now());

        userEntityRepository.save(userEntity);
        log.info("saved or updated userDetails Successfully");
    }
}
