package com.evaccine.user.controller;

import static com.evaccine.user.constants.UserServiceConstants.FETCH_USER_DETAILS_SUCCESS_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.USER_DETAILS_REGISTER_SUCCESS_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.USER_DETAILS_UPDATE_SUCCESS_MESSAGE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.evaccine.user.TestUtil;
import com.evaccine.user.model.UserRegisterRequest;
import com.evaccine.user.model.UserRegisterResponse;
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
        mockMvc.perform(post("/user/update").with(csrf()).contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(requestBody))).andExpect(status().is2xxSuccessful())
                .andExpect(content()
                        .string("{\"message\":\"User Details Updated Successfully\",\"httpStatus\":\"OK\"}"));
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

}
