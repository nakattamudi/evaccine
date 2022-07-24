package com.evaccine.user.constants;

public class UserServiceConstants {

    public static final String USER = "user";
    public static final String REGISTER_USER_INFO_MAPPING = "register";
    public static final String FETCH_USER_INFO_MAPPING = "fetch/{aadhar}";
    public static final String UPDATE_USER_INFO_MAPPING = "update";
    public static final String FETCH_VACCINE_INFO_MAPPING = "fetch/vaccine_info/{countryCode}/{pincode}";
    public static final int MAX_ALLOWED_USERNAME_LENGTH = 50;
    public static final int MAX_ALLOWED_COUNTRY_CODE_LENGTH = 3;
    public static final String USER_NAME_MANDATORY = "userName is Mandatory";
    public static final String USER_NAME_MAX_LENGTH_MESSAGE = "userName should not be more than "
            + MAX_ALLOWED_USERNAME_LENGTH + " char";
    public static final String AADHAR_MANDATORY = "aadhar is Mandatory";
    public static final String AADHAR_PATTERN = "^[2-9]{1}[0-9]{3}[\\s]?[0-9]{4}[\\s]?[0-9]{4}$";
    public static final String AADHAR_INVALID_MESSAGE = "Invalid aadhar provided.Please check";
    public static final String EMAIL_ID_PATTERN = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    public static final String EMAIL_ID_MANDATORY = "emailId is Mandatory";
    public static final String EMAIL_ID_INVALID_MESSAGE = "Invalid emailId provided.Please check";
    public static final String MOBILE_NUMBER_MANDATORY = "mobileNumber is Mandatory";
    public static final String MOBILE_NUMBER_PATTERN = "^[1-9]{1}[0-9]{9}$";
    public static final String MOBILE_NUMBER_INVALID_MESSAGE = "Invalid mobileNumber provided.Please check";
    public static final String COUNTRY_CODE_MANDATORY = "countryCode is Mandatory";
    public static final String PIN_CODE_MANDATORY = "pincode is Mandatory";
    public static final String COUNTRY_CODE_MAX_LENGTH_MESSAGE = "countryCode should not be more than "
            + MAX_ALLOWED_COUNTRY_CODE_LENGTH + " char";
    public static final String USER_DETAILS_REGISTER_SUCCESS_MESSAGE = "User Details Registered Successfully";
    public static final String USER_DETAILS_UPDATE_SUCCESS_MESSAGE = "User Details Updated Successfully";
    public static final String FETCH_USER_DETAILS_SUCCESS_MESSAGE = "User Details Fetched Successfully";
    public static final String FETCH_USER_DETAILS_FAILED = "No Details found for given Aadhar : ";

}
