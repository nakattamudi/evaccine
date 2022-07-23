package com.evaccine.user.service.impl;

import static com.evaccine.user.constants.UserServiceConstants.FETCH_USER_DETAILS_FAILED;
import static com.evaccine.user.constants.UserServiceConstants.FETCH_USER_DETAILS_SUCCESS_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.USER;
import static com.evaccine.user.constants.UserServiceConstants.USER_DETAILS_REGISTER_SUCCESS_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.USER_DETAILS_UPDATE_SUCCESS_MESSAGE;

import java.time.LocalDateTime;

import com.evaccine.user.entity.UserEntity;
import com.evaccine.user.model.UserRegisterRequest;
import com.evaccine.user.model.UserRegisterResponse;
import com.evaccine.user.repository.UserEntityRepository;
import com.evaccine.user.service.UserService;
import com.evaccine.user.validator.UserServiceValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserServiceValidator userServiceValidator;

    @Autowired
    private UserEntityRepository userEntityRepository;

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
