package com.evaccine.user.validator;


import static com.evaccine.user.constants.UserServiceConstants.AADHAR_INVALID_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.AADHAR_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.AADHAR_PATTERN;
import static com.evaccine.user.constants.UserServiceConstants.COUNTRY_CODE_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.COUNTRY_CODE_MAX_LENGTH_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.DOSAGE_NUMBER_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.EMAIL_ID_INVALID_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.EMAIL_ID_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.EMAIL_ID_PATTERN;
import static com.evaccine.user.constants.UserServiceConstants.HOSPITAL_NAME_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.HOSPITAL_NAME_MAX_LENGTH_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.HOSPITAL_PINCODE_STATUS_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.MAX_ALLOWED_COUNTRY_CODE_LENGTH;
import static com.evaccine.user.constants.UserServiceConstants.MAX_ALLOWED_HOSPITAL_NAME_LENGTH;
import static com.evaccine.user.constants.UserServiceConstants.MAX_ALLOWED_USERNAME_LENGTH;
import static com.evaccine.user.constants.UserServiceConstants.MAX_ALLOWED_VACCINE_NAME_LENGTH;
import static com.evaccine.user.constants.UserServiceConstants.MOBILE_NUMBER_INVALID_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.MOBILE_NUMBER_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.MOBILE_NUMBER_PATTERN;
import static com.evaccine.user.constants.UserServiceConstants.USER_NAME_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.USER_NAME_MAX_LENGTH_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.VACCINE_NAME_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.VACCINE_NAME_MAX_LENGTH_MESSAGE;

import java.util.regex.Pattern;

import com.evaccine.user.model.UserRegisterRequest;
import com.evaccine.user.model.VaccineRegisterRequest;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UserServiceValidator {

    public boolean validateUserDetails(final UserRegisterRequest userRegisterRequest) {

        Assert.isTrue(StringUtils.isNotBlank(userRegisterRequest.getUserName()), USER_NAME_MANDATORY);
        Assert.isTrue(userRegisterRequest.getUserName().length() < MAX_ALLOWED_USERNAME_LENGTH,
                USER_NAME_MAX_LENGTH_MESSAGE);

        Assert.isTrue(StringUtils.isNotBlank(userRegisterRequest.getAadharNumber()), AADHAR_MANDATORY);
        Assert.isTrue(Pattern.matches(AADHAR_PATTERN, userRegisterRequest.getAadharNumber()),
                AADHAR_INVALID_MESSAGE);

        Assert.isTrue(StringUtils.isNotBlank(userRegisterRequest.getEmailId()), EMAIL_ID_MANDATORY);
        Assert.isTrue(Pattern.matches(EMAIL_ID_PATTERN, userRegisterRequest.getEmailId()),
                EMAIL_ID_INVALID_MESSAGE);

        Assert.isTrue(StringUtils.isNotBlank(userRegisterRequest.getMobileNumber()), MOBILE_NUMBER_MANDATORY);
        Assert.isTrue(Pattern.matches(MOBILE_NUMBER_PATTERN, userRegisterRequest.getMobileNumber()),
                MOBILE_NUMBER_INVALID_MESSAGE);

        Assert.isTrue(StringUtils.isNotBlank(userRegisterRequest.getCountryCode()), COUNTRY_CODE_MANDATORY);
        Assert.isTrue(userRegisterRequest.getCountryCode().length() <= MAX_ALLOWED_COUNTRY_CODE_LENGTH,
                COUNTRY_CODE_MAX_LENGTH_MESSAGE);
        return true;
    }


    public boolean validateVaccineRegisterRequest(final VaccineRegisterRequest vaccineRegisterRequest) {

        Assert.isTrue(StringUtils.isNotBlank(vaccineRegisterRequest.getAadharNumber()), AADHAR_MANDATORY);
        Assert.isTrue(Pattern.matches(AADHAR_PATTERN, vaccineRegisterRequest.getAadharNumber()),
                AADHAR_INVALID_MESSAGE);

        Assert.isTrue(StringUtils.isNotBlank(vaccineRegisterRequest.getVaccineName()), VACCINE_NAME_MANDATORY);
        Assert.isTrue(vaccineRegisterRequest.getVaccineName().length() < MAX_ALLOWED_VACCINE_NAME_LENGTH,
                VACCINE_NAME_MAX_LENGTH_MESSAGE);

        Assert.isTrue(StringUtils.isNotBlank(vaccineRegisterRequest.getHospitalName()), HOSPITAL_NAME_MANDATORY);
        Assert.isTrue(vaccineRegisterRequest.getHospitalName().length() <= MAX_ALLOWED_HOSPITAL_NAME_LENGTH,
                HOSPITAL_NAME_MAX_LENGTH_MESSAGE);

        Assert.isTrue(StringUtils.isNotBlank(vaccineRegisterRequest.getHospitalPincode()),
                HOSPITAL_PINCODE_STATUS_MANDATORY);

        Assert.isTrue(StringUtils.isNotBlank(vaccineRegisterRequest.getCountryCode()), COUNTRY_CODE_MANDATORY);
        Assert.isTrue(vaccineRegisterRequest.getCountryCode().length() <= MAX_ALLOWED_COUNTRY_CODE_LENGTH,
                COUNTRY_CODE_MAX_LENGTH_MESSAGE);

        Assert.isTrue(vaccineRegisterRequest.getDosageNumber() > 0, DOSAGE_NUMBER_MANDATORY);
        return true;
    }
}
