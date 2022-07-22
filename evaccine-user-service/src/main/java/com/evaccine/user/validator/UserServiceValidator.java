package com.evaccine.user.validator;


import static com.evaccine.user.constants.UserServiceConstants.AADHAR_INVALID_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.AADHAR_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.AADHAR_PATTERN;
import static com.evaccine.user.constants.UserServiceConstants.COUNTRY_CODE_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.COUNTRY_CODE_MAX_LENGTH_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.EMAIL_ID_INVALID_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.EMAIL_ID_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.EMAIL_ID_PATTERN;
import static com.evaccine.user.constants.UserServiceConstants.MAX_ALLOWED_COUNTRY_CODE_LENGTH;
import static com.evaccine.user.constants.UserServiceConstants.MAX_ALLOWED_USERNAME_LENGTH;
import static com.evaccine.user.constants.UserServiceConstants.MOBILE_NUMBER_INVALID_MESSAGE;
import static com.evaccine.user.constants.UserServiceConstants.MOBILE_NUMBER_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.MOBILE_NUMBER_PATTERN;
import static com.evaccine.user.constants.UserServiceConstants.USER_NAME_MANDATORY;
import static com.evaccine.user.constants.UserServiceConstants.USER_NAME_MAX_LENGTH_MESSAGE;

import java.util.regex.Pattern;

import com.evaccine.user.model.UserRegisterRequest;
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
        Assert.isTrue(userRegisterRequest.getCountryCode().length() < MAX_ALLOWED_COUNTRY_CODE_LENGTH,
                COUNTRY_CODE_MAX_LENGTH_MESSAGE);
        return true;
    }
}
