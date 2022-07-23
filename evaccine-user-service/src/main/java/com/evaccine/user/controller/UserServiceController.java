package com.evaccine.user.controller;

import static com.evaccine.user.constants.UserServiceConstants.FETCH_USER_INFO_MAPPING;
import static com.evaccine.user.constants.UserServiceConstants.REGISTER_USER_INFO_MAPPING;
import static com.evaccine.user.constants.UserServiceConstants.UPDATE_USER_INFO_MAPPING;
import static com.evaccine.user.constants.UserServiceConstants.USER;

import com.evaccine.user.model.UserRegisterRequest;
import com.evaccine.user.model.UserRegisterResponse;
import com.evaccine.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(USER)
public class UserServiceController {

    @Autowired
    private UserService userService;

    @PostMapping(REGISTER_USER_INFO_MAPPING)
    public ResponseEntity<UserRegisterResponse> registerUserDetails(@RequestBody final UserRegisterRequest userDetailsRequest) {
        log.info("registerUserDetails: userDetailsRequest : {} ", userDetailsRequest);
        UserRegisterResponse userRegisterResponse = userService.registerUserDetails(userDetailsRequest);
        return new ResponseEntity<>(userRegisterResponse, HttpStatus.OK);
    }

    @GetMapping(FETCH_USER_INFO_MAPPING)
    public ResponseEntity<UserRegisterResponse> getUserDetails(@PathVariable(name = "aadhar") final String id) {
        log.info("getUserDetails: id : {} ", id);
        UserRegisterResponse userRegisterResponse = userService.getUserDetails(id);
        return new ResponseEntity<>(userRegisterResponse, HttpStatus.OK);
    }

    @PutMapping(UPDATE_USER_INFO_MAPPING)
    public ResponseEntity<UserRegisterResponse> updateUserDetails(@RequestBody final UserRegisterRequest userDetailsRequest) {
        log.info("updateUserDetails : userDetailsRequest : {} ", userDetailsRequest);
        UserRegisterResponse userRegisterResponse = userService.updateUserDetails(userDetailsRequest);
        return new ResponseEntity<>(userRegisterResponse, HttpStatus.OK);
    }

}
