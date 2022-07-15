package com.evaccine.admin.constants;

public class AdminServiceConstants {

	public static final String ADMIN = "admin";
	public static final String REGISTER_COUNTRY_INFO_MAPPING = "register/country_info";
	public static final String REGISTER_VACCINE_INFO_MAPPING = "register/vaccine_info";
	public static final String DEACTIVATE_COUNTRY_INFO_MAPPING = "deactivate/country/{countryCode}";
	public static final String DEACTIVATE_VACCINE_INFO_MAPPING = "deactivate/vaccine_info";
	public static final String FETCH_COUNTRY_INFO_MAPPING = "fetch/country/{countryCode}";
	public static final String FETCH_VACCINE_INFO_MAPPING = "fetch/vaccine_info/{countryCode}/{pincode}";
	public static final String UPDATE_COUNTRY_INFO_MAPPING = "update/country_info";
	public static final String UPDATE_VACCINE_INFO_MAPPING = "update/vaccine_info";

	public static final int MAX_ALLOWED_COUNTRY_NAME_LENGTH = 50;
	public static final int MAX_ALLOWED_COUNTRY_CODE_LENGTH = 3;

	public static final int MAX_ALLOWED_VACCINE_NAME_LENGTH = 30;
	public static final int MAX_ALLOWED_HOSPITAL_NAME_LENGTH = 40;

	public static final String COUNTRY_REGISTER_SUCCESS_MESSAGE = "Country Details Registered Successfully";
	public static final String COUNTRY_UPDATE_SUCCESS_MESSAGE = "Country Details Updated Successfully";
	public static final String COUNTRY_INFO_FETCH_FAILURE_MESSAGE = "No Country Info found for given countryCode :";
	public static final String COUNTRY_NAME_MANDATORY = "countryName is Mandatory";
	public static final String COUNTRY_CODE_MANDATORY = "countryCode is Mandatory";
	public static final String COUNTRY_STATUS_MANDATORY = "countryStatus is Mandatory";
	public static final String PIN_CODES_MANDATORY = "pincodes should not be empty";
	public static final String PIN_CODE_MANDATORY = "pincode is Mandatory";
	public static final String COUNTRY_NAME_MAX_LENGTH_MESSAGE = "countryName should not be more than "
			+ MAX_ALLOWED_COUNTRY_NAME_LENGTH + " char";
	public static final String COUNTRY_CODE_MAX_LENGTH_MESSAGE = "countryCode should not be more than "
			+ MAX_ALLOWED_COUNTRY_CODE_LENGTH + " char";

	public static final String VACCINE_REGISTER_SUCCESS_MESSAGE = "Vaccine Details Registered Successfully";
	public static final String VACCINE_UPDATE_SUCCESS_MESSAGE = "Vaccine Details Updated Successfully";
	public static final String VACCINE_NAME_MANDATORY = "vaccineName is Mandatory";
	public static final String HOSPITAL_NAME_MANDATORY = "hospitalName is Mandatory";
	public static final String MAX_APPLICABLE_DOSES_MANDATORY = "maxApplicableDoses is Mandatory and should be >0 and <=100";
	public static final String MAX_APPLICABLE_DOSES_LIMIT_EXCEED_MESSAGE = "maxApplicableDoses should be <100";
	public static final String MIN_DIFFERENCE_NEEDED_MANDATORY = "minDaysDifferenceNeeded is Mandatory and should be >0 and <=1000";
	public static final String MIN_DIFFERENCE_NEEDED_LIMIT_EXCEED_MESSAGE = "minDaysDifferenceNeeded should be <=1000";
	public static final String HOSPITAL_STATUS_MANDATORY = "hospitalStatus is Mandatory";
	public static final String HOSPITAL_PINCODE_STATUS_MANDATORY = "hospitalPincode is Mandatory";

	public static final String VACCINE_NAME_MAX_LENGTH_MESSAGE = "vaccineName should not be more than "
			+ MAX_ALLOWED_VACCINE_NAME_LENGTH + " char";
	public static final String HOSPITAL_NAME_MAX_LENGTH_MESSAGE = "hospitalName should not be more than "
			+ MAX_ALLOWED_HOSPITAL_NAME_LENGTH + " char";

	public static final String USER_REGISTER_SUCCESS = "User Registered Successfully";

	public static final String COUNTRY_DEACTIVATED_SUCCESS_MESSAGE = "Country Deactivated Successfully";
	public static final String COUNTRY_DEACTIVATED_FAILURE_MESSAGE = "No Details found for given countrycode: ";
	public static final String VACCINE_DEACTIVATED_SUCCESS_MESSAGE = "Vaccine Details Deactivated Successfully";
	public static final String VACCINE_DEACTIVATED_FAILURE_MESSAGE = "Vaccine Details Deactivation failed";
}
