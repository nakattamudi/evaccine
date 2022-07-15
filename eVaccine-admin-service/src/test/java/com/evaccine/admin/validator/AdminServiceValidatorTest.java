package com.evaccine.admin.validator;

import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_CODE_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_CODE_MAX_LENGTH_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_NAME_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_NAME_MAX_LENGTH_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.HOSPITAL_NAME_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.HOSPITAL_NAME_MAX_LENGTH_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.HOSPITAL_PINCODE_STATUS_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.MAX_APPLICABLE_DOSES_LIMIT_EXCEED_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.MAX_APPLICABLE_DOSES_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.MIN_DIFFERENCE_NEEDED_LIMIT_EXCEED_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.MIN_DIFFERENCE_NEEDED_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.PIN_CODES_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.VACCINE_NAME_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.VACCINE_NAME_MAX_LENGTH_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.evaccine.admin.model.CountryInfoRequest;
import com.evaccine.admin.model.VaccineInfoRequest;

@ExtendWith(MockitoExtension.class)
public class AdminServiceValidatorTest {

	@InjectMocks
	private AdminServiceValidator adminServiceValidator;

	@Test
	public void registerCountryInfoWithEmptyCountryName() {

		CountryInfoRequest countryInfoRequest = new CountryInfoRequest();

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateCountryInfo(countryInfoRequest);
		});
		assertEquals(exception.getMessage(), COUNTRY_NAME_MANDATORY);
	}

	@Test
	public void registerCountryInfoWithCountryNameExceedingMaxLength() {

		CountryInfoRequest countryInfoRequest = new CountryInfoRequest();
		countryInfoRequest
				.setCountryName("INDIA IS A COUNTRY WHICH HAS 5 CHARACTERS AND INDIA CAN HAVE 3 DIGIT COUNTRY CODE");

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateCountryInfo(countryInfoRequest);
		});
		assertEquals(exception.getMessage(), COUNTRY_NAME_MAX_LENGTH_MESSAGE);
	}

	@Test
	public void registerCountryInfoWithEmptyCountryCode() {

		CountryInfoRequest countryInfoRequest = new CountryInfoRequest();
		countryInfoRequest.setCountryName("INDIA");
		countryInfoRequest.setCountryCode("");

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateCountryInfo(countryInfoRequest);
		});
		assertEquals(exception.getMessage(), COUNTRY_CODE_MANDATORY);
	}

	@Test
	public void registerCountryInfoWithCountryCodeExceedingMaxLength() {

		CountryInfoRequest countryInfoRequest = new CountryInfoRequest();
		countryInfoRequest.setCountryName("INDIA");
		countryInfoRequest.setCountryCode("INDIAN COUNTRY CODE");

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateCountryInfo(countryInfoRequest);
		});
		assertEquals(exception.getMessage(), COUNTRY_CODE_MAX_LENGTH_MESSAGE);
	}

	@Test
	public void registerCountryInfoWithEmptyPinCodes() {

		CountryInfoRequest countryInfoRequest = new CountryInfoRequest();
		countryInfoRequest.setCountryName("INDIA");
		countryInfoRequest.setCountryCode("IND");

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateCountryInfo(countryInfoRequest);
		});
		assertEquals(exception.getMessage(), PIN_CODES_MANDATORY);
	}

	@Test
	public void registerCountryInfoWithValidEntry() {

		CountryInfoRequest countryInfoRequest = new CountryInfoRequest();
		countryInfoRequest.setCountryName("INDIA");
		countryInfoRequest.setCountryCode("IND");
		countryInfoRequest.setPincodes(List.of("12345", "23456"));

		boolean isSuccessFlag = adminServiceValidator.validateCountryInfo(countryInfoRequest);
		assertTrue(isSuccessFlag);

	}

	@Test
	public void registerVaccineWithEmptyVaccineName() {

		VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateVaccineInfo(vaccineInfoRequest, true);
		});
		assertEquals(exception.getMessage(), VACCINE_NAME_MANDATORY);
	}

	@Test
	public void registerVaccineWithVaccineNameExceedingMaxLength() {

		VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();
		vaccineInfoRequest.setVaccineName("This is covaxin message to deliver in India and USA markets");

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateVaccineInfo(vaccineInfoRequest, true);
		});
		assertEquals(exception.getMessage(), VACCINE_NAME_MAX_LENGTH_MESSAGE);
	}

	@Test
	public void registerVaccineWithEmptyCountryCode() {

		VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();
		vaccineInfoRequest.setVaccineName("COVAXIN");

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateVaccineInfo(vaccineInfoRequest, true);
		});
		assertEquals(exception.getMessage(), COUNTRY_CODE_MANDATORY);
	}

	@Test
	public void registerVaccineWithCountryCodeExceedingMaxLength() {

		VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();
		vaccineInfoRequest.setVaccineName("COVAXIN");
		vaccineInfoRequest.setCountryCode("INDIA");

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateVaccineInfo(vaccineInfoRequest, true);
		});
		assertEquals(exception.getMessage(), COUNTRY_CODE_MAX_LENGTH_MESSAGE);
	}

	@Test
	public void registerVaccineWithEmptyHospitalPincode() {

		VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();
		vaccineInfoRequest.setVaccineName("COVAXIN");
		vaccineInfoRequest.setCountryCode("IND");

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateVaccineInfo(vaccineInfoRequest, true);
		});
		assertEquals(exception.getMessage(), HOSPITAL_PINCODE_STATUS_MANDATORY);
	}

	@Test
	public void registerVaccineWithEmptyHospitalName() {

		VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();
		vaccineInfoRequest.setVaccineName("COVAXIN");
		vaccineInfoRequest.setCountryCode("IND");
		vaccineInfoRequest.setHospitalPincode("21323");
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateVaccineInfo(vaccineInfoRequest, true);
		});
		assertEquals(exception.getMessage(), HOSPITAL_NAME_MANDATORY);
	}

	@Test
	public void registerVaccineWithHospitalNameExceedingMaxLength() {

		VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();
		vaccineInfoRequest.setVaccineName("COVAXIN");
		vaccineInfoRequest.setCountryCode("IND");
		vaccineInfoRequest.setHospitalPincode("21323");
		vaccineInfoRequest.setHospitalName("This is ICON hospital located in Hyderabad and in Gachibowli");

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateVaccineInfo(vaccineInfoRequest, true);
		});
		assertEquals(exception.getMessage(), HOSPITAL_NAME_MAX_LENGTH_MESSAGE);
	}

	@Test
	public void registerVaccineWithEmptyMaxApplicableDosesField() {

		VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();
		vaccineInfoRequest.setVaccineName("COVAXIN");
		vaccineInfoRequest.setCountryCode("IND");
		vaccineInfoRequest.setHospitalPincode("21323");
		vaccineInfoRequest.setHospitalName("ICON hospital");
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateVaccineInfo(vaccineInfoRequest, true);
		});
		assertEquals(exception.getMessage(), MAX_APPLICABLE_DOSES_MANDATORY);
	}

	@Test
	public void registerVaccineWithMaxApplicableDosesExceedingLength() {

		VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();
		vaccineInfoRequest.setVaccineName("COVAXIN");
		vaccineInfoRequest.setCountryCode("IND");
		vaccineInfoRequest.setHospitalPincode("21323");
		vaccineInfoRequest.setHospitalName("ICON hospital");
		vaccineInfoRequest.setMaxApplicableDoses(123);
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateVaccineInfo(vaccineInfoRequest, true);
		});
		assertEquals(exception.getMessage(), MAX_APPLICABLE_DOSES_LIMIT_EXCEED_MESSAGE);
	}

	@Test
	public void registerVaccineWithEmptyMinDaysDifferenceNeededField() {

		VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();
		vaccineInfoRequest.setVaccineName("COVAXIN");
		vaccineInfoRequest.setCountryCode("IND");
		vaccineInfoRequest.setHospitalPincode("21323");
		vaccineInfoRequest.setHospitalName("ICON hospital");
		vaccineInfoRequest.setMaxApplicableDoses(5);
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateVaccineInfo(vaccineInfoRequest, true);
		});
		assertEquals(exception.getMessage(), MIN_DIFFERENCE_NEEDED_MANDATORY);
	}

	@Test
	public void registerVaccineWithMinDaysDifferenceNeededExceedingLength() {

		VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();
		vaccineInfoRequest.setVaccineName("COVAXIN");
		vaccineInfoRequest.setCountryCode("IND");
		vaccineInfoRequest.setHospitalPincode("21323");
		vaccineInfoRequest.setHospitalName("ICON hospital");
		vaccineInfoRequest.setMaxApplicableDoses(5);
		vaccineInfoRequest.setMinDaysDifferenceNeeded(3000);
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			adminServiceValidator.validateVaccineInfo(vaccineInfoRequest, true);
		});
		assertEquals(exception.getMessage(), MIN_DIFFERENCE_NEEDED_LIMIT_EXCEED_MESSAGE);
	}

	@Test
	public void registerVaccineWithValidData() {

		VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();
		vaccineInfoRequest.setVaccineName("COVAXIN");
		vaccineInfoRequest.setCountryCode("IND");
		vaccineInfoRequest.setHospitalPincode("21323");
		vaccineInfoRequest.setHospitalName("ICON hospital");
		vaccineInfoRequest.setMaxApplicableDoses(5);
		vaccineInfoRequest.setMinDaysDifferenceNeeded(300);

		boolean isSuccessFlag = adminServiceValidator.validateVaccineInfo(vaccineInfoRequest, true);
		assertTrue(isSuccessFlag);
	}

}
