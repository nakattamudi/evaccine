package com.evaccine.user.validator;


import static com.evaccine.user.constants.UserServiceConstants.AADHAR_INVALID_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.AADHAR_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.COUNTRY_CODE_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.COUNTRY_CODE_MAX_LENGTH_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.DOSAGE_NUMBER_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.EMAIL_ID_INVALID_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.EMAIL_ID_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.HOSPITAL_NAME_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.HOSPITAL_NAME_MAX_LENGTH_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.HOSPITAL_PINCODE_STATUS_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.MOBILE_NUMBER_INVALID_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.MOBILE_NUMBER_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.USER_NAME_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.USER_NAME_MAX_LENGTH_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.VACCINE_NAME_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.VACCINE_NAME_MAX_LENGTH_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.evaccine.user.model.UserRegisterRequest;
import com.evaccine.user.model.VaccineRegisterRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceValidatorTest {


    @InjectMocks
    private UserServiceValidator userServiceValidator;


    @Test
    public void validateUserDetailsWithEmptyUserName() {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateUserDetails(userRegisterRequest);
        });
        assertEquals(exception.getMessage(), USER_NAME_MANDATORY);
    }

    @Test
    public void validateUserDetailsWithUserNameExceedingMaxLength() {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUserName("Hello, This is a check to validate max userName in unit test cases");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateUserDetails(userRegisterRequest);
        });
        assertEquals(exception.getMessage(), USER_NAME_MAX_LENGTH_MESSAGE);
    }

    @Test
    public void validateUserDetailsWithEmptyAadharNumber() {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUserName("JUNIT test case");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateUserDetails(userRegisterRequest);
        });
        assertEquals(exception.getMessage(), AADHAR_MANDATORY);
    }

    @Test
    public void validateUserDetailsWithInvalidAadharNumber() {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUserName("JUNIT test case");
        userRegisterRequest.setAadharNumber("2332sdewe34");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateUserDetails(userRegisterRequest);
        });
        assertEquals(exception.getMessage(), AADHAR_INVALID_MESSAGE);
    }

    @Test
    public void validateUserDetailsWithEmptyEmailId() {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUserName("JUNIT test case");
        userRegisterRequest.setAadharNumber("987654322345");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateUserDetails(userRegisterRequest);
        });
        assertEquals(exception.getMessage(), EMAIL_ID_MANDATORY);
    }

    @Test
    public void validateUserDetailsWithInvalidEmailId() {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUserName("JUNIT test case");
        userRegisterRequest.setAadharNumber("987654322345");
        userRegisterRequest.setEmailId("test.com");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateUserDetails(userRegisterRequest);
        });
        assertEquals(exception.getMessage(), EMAIL_ID_INVALID_MESSAGE);
    }


    @Test
    public void validateUserDetailsWithEmptyMobileNumber() {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUserName("JUNIT test case");
        userRegisterRequest.setAadharNumber("987654322345");
        userRegisterRequest.setEmailId("test@mail.com");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateUserDetails(userRegisterRequest);
        });
        assertEquals(exception.getMessage(), MOBILE_NUMBER_MANDATORY);
    }

    @Test
    public void validateUserDetailsWithInvalidMobileNumber() {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUserName("JUNIT test case");
        userRegisterRequest.setAadharNumber("987654322345");
        userRegisterRequest.setEmailId("test@mail.com");
        userRegisterRequest.setMobileNumber("100");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateUserDetails(userRegisterRequest);
        });
        assertEquals(exception.getMessage(), MOBILE_NUMBER_INVALID_MESSAGE);
    }


    @Test
    public void validateUserDetailsWithEmptyCountryCode() {

        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUserName("JUNIT test case");
        userRegisterRequest.setAadharNumber("987654322345");
        userRegisterRequest.setEmailId("test@mail.com");
        userRegisterRequest.setMobileNumber("8008001100");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateUserDetails(userRegisterRequest);
        });
        assertEquals(exception.getMessage(), COUNTRY_CODE_MANDATORY);
    }

    @Test
    public void validateUserDetailsWithInvalidCountryCode() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUserName("JUNIT test case");
        userRegisterRequest.setAadharNumber("987654322345");
        userRegisterRequest.setEmailId("test@mail.com");
        userRegisterRequest.setMobileNumber("8008001100");
        userRegisterRequest.setCountryCode("12312");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateUserDetails(userRegisterRequest);
        });
        assertEquals(exception.getMessage(), COUNTRY_CODE_MAX_LENGTH_MESSAGE);
    }


    @Test
    public void validateUserDetailsWithValidData() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setUserName("JUNIT test case");
        userRegisterRequest.setAadharNumber("987654322345");
        userRegisterRequest.setEmailId("test@mail.com");
        userRegisterRequest.setMobileNumber("8008001100");
        userRegisterRequest.setCountryCode("123");
        boolean isDataValid = userServiceValidator.validateUserDetails(userRegisterRequest);
        assertTrue(isDataValid);
    }


    @Test
    public void validateVaccineRegisterRequestWithEmptyAadharName() {

        VaccineRegisterRequest vaccineRegisterRequest = new VaccineRegisterRequest();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateVaccineRegisterRequest(vaccineRegisterRequest);
        });
        assertEquals(exception.getMessage(), AADHAR_MANDATORY);
    }

    @Test
    public void validateVaccineRegisterRequestWithInValidAadhar() {

        VaccineRegisterRequest vaccineRegisterRequest = new VaccineRegisterRequest();
        vaccineRegisterRequest.setAadharNumber("5345dfefr");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateVaccineRegisterRequest(vaccineRegisterRequest);
        });
        assertEquals(exception.getMessage(), AADHAR_INVALID_MESSAGE);
    }

    @Test
    public void validateVaccineRegisterRequestWithEmptyVaccineName() {

        VaccineRegisterRequest vaccineRegisterRequest = new VaccineRegisterRequest();
        vaccineRegisterRequest.setAadharNumber("988876545432");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateVaccineRegisterRequest(vaccineRegisterRequest);
        });
        assertEquals(exception.getMessage(), VACCINE_NAME_MANDATORY);
    }

    @Test
    public void validateVaccineRegisterRequestWithExceedingMaxVaccineNameLength() {

        VaccineRegisterRequest vaccineRegisterRequest = new VaccineRegisterRequest();
        vaccineRegisterRequest.setAadharNumber("988876545432");
        vaccineRegisterRequest.setVaccineName("This is to check the validation for max vaccinename with bigger name");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateVaccineRegisterRequest(vaccineRegisterRequest);
        });
        assertEquals(exception.getMessage(), VACCINE_NAME_MAX_LENGTH_MESSAGE);
    }


    @Test
    public void validateVaccineRegisterRequestWithEmptyHospitalName() {

        VaccineRegisterRequest vaccineRegisterRequest = new VaccineRegisterRequest();
        vaccineRegisterRequest.setAadharNumber("988876545432");
        vaccineRegisterRequest.setVaccineName("COVAXIN");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateVaccineRegisterRequest(vaccineRegisterRequest);
        });
        assertEquals(exception.getMessage(), HOSPITAL_NAME_MANDATORY);
    }

    @Test
    public void validateVaccineRegisterRequestWithExceedingMaxHospitalNameLength() {

        VaccineRegisterRequest vaccineRegisterRequest = new VaccineRegisterRequest();
        vaccineRegisterRequest.setAadharNumber("988876545432");
        vaccineRegisterRequest.setVaccineName("COVAXIN");
        vaccineRegisterRequest.setHospitalName("This is to check the validation for max hospitalName with bigger name");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateVaccineRegisterRequest(vaccineRegisterRequest);
        });
        assertEquals(exception.getMessage(), HOSPITAL_NAME_MAX_LENGTH_MESSAGE);
    }


    @Test
    public void validateVaccineRegisterRequestWithEmptyPincode() {

        VaccineRegisterRequest vaccineRegisterRequest = new VaccineRegisterRequest();
        vaccineRegisterRequest.setAadharNumber("988876545432");
        vaccineRegisterRequest.setVaccineName("COVAXIN");
        vaccineRegisterRequest.setHospitalName("ICON");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateVaccineRegisterRequest(vaccineRegisterRequest);
        });
        assertEquals(exception.getMessage(), HOSPITAL_PINCODE_STATUS_MANDATORY);
    }

    @Test
    public void validateVaccineRegisterRequestWithEmptyCountryCode() {

        VaccineRegisterRequest vaccineRegisterRequest = new VaccineRegisterRequest();
        vaccineRegisterRequest.setAadharNumber("988876545432");
        vaccineRegisterRequest.setVaccineName("COVAXIN");
        vaccineRegisterRequest.setHospitalName("ICON");
        vaccineRegisterRequest.setHospitalPincode("1233");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateVaccineRegisterRequest(vaccineRegisterRequest);
        });
        assertEquals(exception.getMessage(), COUNTRY_CODE_MANDATORY);
    }

    @Test
    public void validateVaccineRegisterRequestWithExceedingMaxCountryCodeLength() {

        VaccineRegisterRequest vaccineRegisterRequest = new VaccineRegisterRequest();
        vaccineRegisterRequest.setAadharNumber("988876545432");
        vaccineRegisterRequest.setVaccineName("COVAXIN");
        vaccineRegisterRequest.setHospitalName("ICON");
        vaccineRegisterRequest.setHospitalPincode("1233");
        vaccineRegisterRequest.setCountryCode("122222");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateVaccineRegisterRequest(vaccineRegisterRequest);
        });
        assertEquals(exception.getMessage(), COUNTRY_CODE_MAX_LENGTH_MESSAGE);
    }


    @Test
    public void validateVaccineRegisterRequestWithEmptyDosageCount() {

        VaccineRegisterRequest vaccineRegisterRequest = new VaccineRegisterRequest();
        vaccineRegisterRequest.setAadharNumber("988876545432");
        vaccineRegisterRequest.setVaccineName("COVAXIN");
        vaccineRegisterRequest.setHospitalName("ICON");
        vaccineRegisterRequest.setHospitalPincode("1233");
        vaccineRegisterRequest.setCountryCode("122");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userServiceValidator.validateVaccineRegisterRequest(vaccineRegisterRequest);
        });
        assertEquals(exception.getMessage(), DOSAGE_NUMBER_MANDATORY);
    }


    @Test
    public void validateVaccineRegisterRequestWithValidEntry() {

        VaccineRegisterRequest vaccineRegisterRequest = new VaccineRegisterRequest();
        vaccineRegisterRequest.setAadharNumber("988876545432");
        vaccineRegisterRequest.setVaccineName("COVAXIN");
        vaccineRegisterRequest.setHospitalName("ICON");
        vaccineRegisterRequest.setHospitalPincode("1233");
        vaccineRegisterRequest.setCountryCode("122");
        vaccineRegisterRequest.setDosageNumber(1);
        boolean isValidEntry = userServiceValidator.validateVaccineRegisterRequest(vaccineRegisterRequest);
        assertTrue(isValidEntry);
    }
}
