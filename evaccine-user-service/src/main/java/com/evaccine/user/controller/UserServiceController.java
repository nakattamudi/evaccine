package com.evaccine.user.controller;

import static com.evaccine.user.constants.UserServiceConstants.FETCH_USER_INFO_MAPPING;
import static com.evaccine.user.constants.UserServiceConstants.FETCH_VACCINE_INFO_MAPPING;
import static com.evaccine.user.constants.UserServiceConstants.REGISTER_USER_FOR_VACCINATION;
import static com.evaccine.user.constants.UserServiceConstants.REGISTER_USER_INFO_MAPPING;
import static com.evaccine.user.constants.UserServiceConstants.UPDATE_USER_INFO_MAPPING;
import static com.evaccine.user.constants.UserServiceConstants.USER;

import java.util.List;

import com.evaccine.user.model.UserRegisterRequest;
import com.evaccine.user.model.UserRegisterResponse;
import com.evaccine.user.model.UserVaccineInfoResponse;
import com.evaccine.user.model.VaccineRegisterRequest;
import com.evaccine.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@Api(value = "userservice", description = "CRUD Operations pertaining to user registration for vaccine")
public class UserServiceController {

    @Autowired
    private UserService userService;

    @PostMapping(REGISTER_USER_INFO_MAPPING)
    @ApiOperation(value = "Register User Details within the System", response = UserRegisterResponse.class)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Access forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")})
    public ResponseEntity<UserRegisterResponse> registerUserDetails(@RequestBody final UserRegisterRequest userDetailsRequest) {
        log.info("registerUserDetails: userDetailsRequest : {} ", userDetailsRequest);
        UserRegisterResponse userRegisterResponse = userService.registerUserDetails(userDetailsRequest);
        return new ResponseEntity<>(userRegisterResponse, HttpStatus.OK);
    }

    @GetMapping(FETCH_USER_INFO_MAPPING)
    @ApiOperation(value = "Fetch User Information based on Id provided", response = UserRegisterResponse.class)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Access forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")})
    public ResponseEntity<UserRegisterResponse> getUserDetails(@PathVariable(name = "aadhar") final String id) {
        log.info("getUserDetails: id : {} ", id);
        UserRegisterResponse userRegisterResponse = userService.getUserDetails(id);
        return new ResponseEntity<>(userRegisterResponse, HttpStatus.OK);
    }

    @PutMapping(UPDATE_USER_INFO_MAPPING)
    @ApiOperation(value = "Update User Information based on Id provided", response = UserRegisterResponse.class)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Access forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")})
    public ResponseEntity<UserRegisterResponse> updateUserDetails(@RequestBody final UserRegisterRequest userDetailsRequest) {
        log.info("updateUserDetails : userDetailsRequest : {} ", userDetailsRequest);
        UserRegisterResponse userRegisterResponse = userService.updateUserDetails(userDetailsRequest);
        return new ResponseEntity<>(userRegisterResponse, HttpStatus.OK);
    }

    @GetMapping(FETCH_VACCINE_INFO_MAPPING)
    @ApiOperation(value = "Fetch Vaccine Related Information based on countryCode and pincode", response = List.class)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Access forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")})
    public ResponseEntity<List<UserVaccineInfoResponse>> getVaccineInfo(@PathVariable final String countryCode,
                                                                        @PathVariable final String pincode) {
        log.info("getVaccineInfo : {} , {}  ", countryCode, pincode);
        List<UserVaccineInfoResponse> countryInfoResponseList = userService.fetchVaccineInfo(countryCode, pincode);
        return new ResponseEntity<>(countryInfoResponseList, HttpStatus.OK);
    }

    @PostMapping(REGISTER_USER_FOR_VACCINATION)
    @ApiOperation(value = "Register user for Vaccination", response = UserRegisterResponse.class)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Access forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")})
    public ResponseEntity<UserRegisterResponse> registerUserForVaccination(
            @RequestBody final VaccineRegisterRequest vaccineRegisterRequest) {
        log.info("registerUserForVaccination : userDetailsRequest : {} ", vaccineRegisterRequest);
        UserRegisterResponse userRegisterResponse = userService.vaccineRegisterRequest(vaccineRegisterRequest);
        return new ResponseEntity<>(userRegisterResponse, HttpStatus.OK);
    }

}
