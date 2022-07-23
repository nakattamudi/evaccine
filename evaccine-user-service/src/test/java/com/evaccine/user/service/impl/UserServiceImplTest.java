package com.evaccine.user.service.impl;


import static com.evaccine.user.constants.UserServiceConstants.USER_DETAILS_REGISTER_SUCCESS_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.evaccine.user.entity.UserEntity;
import com.evaccine.user.model.GenderType;
import com.evaccine.user.model.UserRegisterRequest;
import com.evaccine.user.model.UserRegisterResponse;
import com.evaccine.user.repository.UserEntityRepository;
import com.evaccine.user.validator.UserServiceValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserServiceValidator userServiceValidator;
    @Mock
    private UserEntityRepository userEntityRepository;

    @Test
    public void registerUserDetailsTest() {
        UserRegisterRequest userDetailsRequest = UserRegisterRequest.builder().userName("John")
                .aadharNumber("888888876541").emailId("emailId@mail.com")
                .mobileNumber("9999999998").gender(GenderType.MALE).countryCode("222").build();
        UserRegisterResponse userRegisterResponse = userServiceImpl.registerUserDetails(userDetailsRequest);

        assertThat(userRegisterResponse).isNotNull();
        assertThat(userRegisterResponse.getMessage()).isNotNull();
        assertEquals(userRegisterResponse.getMessage(), USER_DETAILS_REGISTER_SUCCESS_MESSAGE);
        verify(userEntityRepository, times(1)).findByAadharNumber(any(String.class));
        verify(userEntityRepository, times(1)).save(any(UserEntity.class));
    }
}
