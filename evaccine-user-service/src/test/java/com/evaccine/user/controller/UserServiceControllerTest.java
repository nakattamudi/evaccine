package com.evaccine.user.controller;

import static com.evaccine.user.constants.UserServiceConstants.FETCH_USER_DETAILS_SUCCESS_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.USER_DETAILS_REGISTER_SUCCESS_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.USER_DETAILS_UPDATE_SUCCESS_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.USER_REGISTER_FOR_VACCINATION_SUCCESS_MESSAGE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.evaccine.user.TestUtil;
import com.evaccine.user.model.UserRegisterRequest;
import com.evaccine.user.model.UserRegisterResponse;
import com.evaccine.user.model.UserVaccineInfoResponse;
import com.evaccine.user.model.VaccineRegisterRequest;
import com.evaccine.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest
public class UserServiceControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void registerUserDetails() throws Exception {

        var requestBody = TestUtil.readFile("src/test/java/testData/registerNewUser.json");
        mockRegisterUserInfoResponseForValidData();
        mockMvc.perform(post("/user/register").with(csrf()).contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(requestBody))).andExpect(status().is2xxSuccessful())
                .andExpect(content()
                        .string("{\"message\":\"User Details Registered Successfully\",\"httpStatus\":\"OK\"}"));
    }

    @Test
    @WithMockUser
    public void fetchUserDetails() throws Exception {

        mockFetchUserInfoResponseForValidData();
        mockMvc.perform(get("/user/fetch/userid")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(content()
                        .string("{\"message\":\"User Details Fetched Successfully\",\"httpStatus\":\"OK\"}"));
    }


    @Test
    public void updateUserDetails() throws Exception {

        var requestBody = TestUtil.readFile("src/test/java/testData/updateUser.json");
        mockUpdateUserInfoResponseForValidData();
        mockMvc.perform(put("/user/update").with(csrf()).contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(requestBody))).andExpect(status().is2xxSuccessful())
                .andExpect(content()
                        .string("{\"message\":\"User Details Updated Successfully\",\"httpStatus\":\"OK\"}"));
    }

    @Test
    @WithMockUser
    public void fetchVaccineInfo() throws Exception {
        mockFetchVaccineInfoResponseForValidData();
        mockMvc.perform(get("/user/fetch/vaccine_info/IND/1233").with(csrf())).andExpect(status().is2xxSuccessful())
                .andExpect(content()
                        .string("[{\"vaccineName\":\"COVAXIN\",\"hospitalName\":\"ICON\",\"hospitalPincode\":\"1233\","
                                + "\"countryCode\":\"IND\"},{\"vaccineName\":\"FIZER\",\"hospitalName\":\"ICON\","
                                + "\"hospitalPincode\":\"1233\",\"countryCode\":\"IND\"}]"));
    }


    @Test
    @WithMockUser
    public void registerUserForVaccination() throws Exception {

        var requestBody = TestUtil.readFile("src/test/java/testData/registerUserForVaccination.json");
        mockRegisterUserForVaccinationValidData();
        mockMvc.perform(post("/user/register/vaccination").with(csrf()).contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(requestBody))).andExpect(status().is2xxSuccessful())
                .andExpect(content()
                        .string("{\"message\":\"User Registered for Vaccination Successfully\",\"httpStatus\":\"OK\"}"));
    }

    private void mockRegisterUserForVaccinationValidData() {

        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setMessage(USER_REGISTER_FOR_VACCINATION_SUCCESS_MESSAGE);
        userRegisterResponse.setHttpStatus(HttpStatus.OK);
        when(userService.vaccineRegisterRequest(any(VaccineRegisterRequest.class))).thenReturn(userRegisterResponse);
    }

    private void mockRegisterUserInfoResponseForValidData() {
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setMessage(USER_DETAILS_REGISTER_SUCCESS_MESSAGE);
        userRegisterResponse.setHttpStatus(HttpStatus.OK);
        when(userService.registerUserDetails(any(UserRegisterRequest.class))).thenReturn(userRegisterResponse);
    }


    private void mockUpdateUserInfoResponseForValidData() {
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setMessage(USER_DETAILS_UPDATE_SUCCESS_MESSAGE);
        userRegisterResponse.setHttpStatus(HttpStatus.OK);
        when(userService.updateUserDetails(any(UserRegisterRequest.class))).thenReturn(userRegisterResponse);
    }

    private void mockFetchUserInfoResponseForValidData() {
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setMessage(FETCH_USER_DETAILS_SUCCESS_MESSAGE);
        userRegisterResponse.setHttpStatus(HttpStatus.OK);
        when(userService.getUserDetails(any(String.class))).thenReturn(userRegisterResponse);
    }

    private void mockFetchVaccineInfoResponseForValidData() {
        List<UserVaccineInfoResponse> vaccineInfoResponseList = new ArrayList<>();
        UserVaccineInfoResponse vaccineInfoResponse = new UserVaccineInfoResponse();

        vaccineInfoResponse.setCountryCode("IND");
        vaccineInfoResponse.setHospitalPincode("1233");
        vaccineInfoResponse.setVaccineName("COVAXIN");
        vaccineInfoResponse.setHospitalName("ICON");

        UserVaccineInfoResponse vaccineInfoResponse2 = new UserVaccineInfoResponse();
        vaccineInfoResponse2.setCountryCode("IND");
        vaccineInfoResponse2.setHospitalPincode("1233");
        vaccineInfoResponse2.setVaccineName("FIZER");
        vaccineInfoResponse2.setHospitalName("ICON");

        vaccineInfoResponseList.add(vaccineInfoResponse);
        vaccineInfoResponseList.add(vaccineInfoResponse2);
        when(userService.fetchVaccineInfo(any(String.class), any(String.class))).thenReturn(vaccineInfoResponseList);
    }

}
