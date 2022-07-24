package com.evaccine.user.service.impl;


import static com.evaccine.user.constants.UserServiceConstants.FETCH_USER_DETAILS_FAILED;
import static com.evaccine.user.constants.UserServiceConstants.FETCH_USER_DETAILS_SUCCESS_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.USER_DETAILS_REGISTER_SUCCESS_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.USER_DETAILS_UPDATE_SUCCESS_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.USER_REGISTER_FOR_VACCINATION_SUCCESS_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import com.evaccine.user.entity.UserEntity;
import com.evaccine.user.entity.VaccineInfoEntity;
import com.evaccine.user.model.GenderType;
import com.evaccine.user.model.UserRegisterRequest;
import com.evaccine.user.model.UserRegisterResponse;
import com.evaccine.user.model.UserVaccineInfoResponse;
import com.evaccine.user.model.VaccineRegisterRequest;
import com.evaccine.user.repository.UserEntityRepository;
import com.evaccine.user.repository.VaccineInfoRepository;
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

    @Mock
    private VaccineInfoRepository vaccineInfoRepository;

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

    @Test
    public void updateUserDetailsTest() {
        UserRegisterRequest userDetailsRequest = UserRegisterRequest.builder().userName("John")
                .aadharNumber("888888876541").emailId("emailId@mail.com")
                .mobileNumber("9999999998").gender(GenderType.MALE).countryCode("222").build();

        when(userEntityRepository.findByAadharNumber(any(String.class))).thenReturn(new UserEntity());
        UserRegisterResponse userRegisterResponse = userServiceImpl.updateUserDetails(userDetailsRequest);

        assertThat(userRegisterResponse).isNotNull();
        assertThat(userRegisterResponse.getMessage()).isNotNull();
        assertEquals(userRegisterResponse.getMessage(), USER_DETAILS_UPDATE_SUCCESS_MESSAGE);
        verify(userEntityRepository, times(1)).findByAadharNumber(any(String.class));
        verify(userEntityRepository, times(1)).save(any(UserEntity.class));
    }


    @Test
    public void fetchUserDetailsSuccessTest() {

        String id = "888888876541";
        when(userEntityRepository.findByAadharNumber(any(String.class))).thenReturn(null);
        UserRegisterResponse userRegisterResponse = userServiceImpl.getUserDetails(id);
        assertThat(userRegisterResponse).isNotNull();
        assertThat(userRegisterResponse.getMessage()).isNotNull();
        assertEquals(userRegisterResponse.getMessage(), FETCH_USER_DETAILS_FAILED + id);
        verify(userEntityRepository, times(1)).findByAadharNumber(any(String.class));
        verify(userEntityRepository, times(0)).save(any(UserEntity.class));
    }

    @Test
    public void fetchUserDetailsWithInvalidIdTest() {

        String id = "888888876541";
        when(userEntityRepository.findByAadharNumber(any(String.class))).thenReturn(new UserEntity());

        UserRegisterResponse userRegisterResponse = userServiceImpl.getUserDetails(id);
        assertThat(userRegisterResponse).isNotNull();
        assertThat(userRegisterResponse.getMessage()).isNotNull();
        assertEquals(userRegisterResponse.getMessage(), FETCH_USER_DETAILS_SUCCESS_MESSAGE);
        verify(userEntityRepository, times(1)).findByAadharNumber(any(String.class));
        verify(userEntityRepository, times(0)).save(any(UserEntity.class));
    }

    @Test
    public void fetchVaccineInfoWithValidEntry() {

        String countryCode = "NZE";
        String pincode = "2133";
        VaccineInfoEntity vaccineInfoEntity = new VaccineInfoEntity();
        when(vaccineInfoRepository.findByHospitalPincodeAndCountryCode(any(String.class), any(String.class)))
                .thenReturn(List.of(vaccineInfoEntity));
        List<UserVaccineInfoResponse> vaccineInfoResponseList = userServiceImpl.fetchVaccineInfo(countryCode, pincode);

        assertThat(vaccineInfoResponseList).isNotEmpty();
        verify(vaccineInfoRepository, times(1)).findByHospitalPincodeAndCountryCode(any(String.class),
                any(String.class));
        verify(vaccineInfoRepository, times(0)).save(any(VaccineInfoEntity.class));
    }

    @Test
    public void fetchVaccineInfoWithInValidEntry() {

        String countryCode = "NZE";
        String pincode = "2133";
        when(vaccineInfoRepository.findByHospitalPincodeAndCountryCode(any(String.class), any(String.class)))
                .thenReturn(null);
        List<UserVaccineInfoResponse> vaccineInfoResponseList = userServiceImpl.fetchVaccineInfo(countryCode, pincode);

        assertThat(vaccineInfoResponseList).isEmpty();
        verify(vaccineInfoRepository, times(1)).findByHospitalPincodeAndCountryCode(any(String.class),
                any(String.class));
        verify(vaccineInfoRepository, times(0)).save(any(VaccineInfoEntity.class));
    }


    @Test
    public void vaccineRegisterRequestWithValidEntry() {

        VaccineRegisterRequest vaccineRegisterRequest = VaccineRegisterRequest.builder()
                .aadharNumber("878666543121")
                .countryCode("IND")
                .dosageNumber(1)
                .hospitalPincode("1233")
                .vaccineRegistrationDate(LocalDateTime.now())
                .vaccineName("FIZER")
                .build();

        UserRegisterResponse vaccineInfoResponse = userServiceImpl.vaccineRegisterRequest(vaccineRegisterRequest);
        assertThat(vaccineInfoResponse).isNotNull();
        assertEquals(vaccineInfoResponse.getMessage(), USER_REGISTER_FOR_VACCINATION_SUCCESS_MESSAGE);
    }

}
