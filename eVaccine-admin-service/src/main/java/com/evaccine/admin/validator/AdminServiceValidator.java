package com.evaccine.admin.validator;

import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_CODE_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_CODE_MAX_LENGTH_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_NAME_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_NAME_MAX_LENGTH_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.HOSPITAL_NAME_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.HOSPITAL_NAME_MAX_LENGTH_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.HOSPITAL_PINCODE_STATUS_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.MAX_ALLOWED_COUNTRY_CODE_LENGTH;
import static com.evaccine.admin.constants.AdminServiceConstants.MAX_ALLOWED_COUNTRY_NAME_LENGTH;
import static com.evaccine.admin.constants.AdminServiceConstants.MAX_ALLOWED_HOSPITAL_NAME_LENGTH;
import static com.evaccine.admin.constants.AdminServiceConstants.MAX_ALLOWED_VACCINE_NAME_LENGTH;
import static com.evaccine.admin.constants.AdminServiceConstants.MAX_APPLICABLE_DOSES_LIMIT_EXCEED_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.MAX_APPLICABLE_DOSES_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.MIN_DIFFERENCE_NEEDED_LIMIT_EXCEED_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.MIN_DIFFERENCE_NEEDED_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.PIN_CODES_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.VACCINE_NAME_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.VACCINE_NAME_MAX_LENGTH_MESSAGE;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.evaccine.admin.model.CountryInfoRequest;
import com.evaccine.admin.model.VaccineInfoRequest;

@Component
public class AdminServiceValidator {

	public boolean validateCountryInfo(final CountryInfoRequest countryInfoRequest) {

		Assert.isTrue(StringUtils.isNotBlank(countryInfoRequest.getCountryName()), COUNTRY_NAME_MANDATORY);
		Assert.isTrue(countryInfoRequest.getCountryName().length() < MAX_ALLOWED_COUNTRY_NAME_LENGTH,
				COUNTRY_NAME_MAX_LENGTH_MESSAGE);

		Assert.isTrue(StringUtils.isNotBlank(countryInfoRequest.getCountryCode()), COUNTRY_CODE_MANDATORY);
		Assert.isTrue(countryInfoRequest.getCountryCode().length() <= MAX_ALLOWED_COUNTRY_CODE_LENGTH,
				COUNTRY_CODE_MAX_LENGTH_MESSAGE);

		Assert.isTrue(!CollectionUtils.isEmpty(countryInfoRequest.getPincodes()), PIN_CODES_MANDATORY);

		return true;
	}

	public boolean validateVaccineInfo(final VaccineInfoRequest vaccineInfoRequest,
			final boolean isCreateVaccineRequest) {
		Assert.isTrue(StringUtils.isNotBlank(vaccineInfoRequest.getVaccineName()), VACCINE_NAME_MANDATORY);
		Assert.isTrue(vaccineInfoRequest.getVaccineName().length() < MAX_ALLOWED_VACCINE_NAME_LENGTH,
				VACCINE_NAME_MAX_LENGTH_MESSAGE);

		Assert.isTrue(StringUtils.isNotBlank(vaccineInfoRequest.getCountryCode()), COUNTRY_CODE_MANDATORY);
		Assert.isTrue(vaccineInfoRequest.getCountryCode().length() <= MAX_ALLOWED_COUNTRY_CODE_LENGTH,
				COUNTRY_CODE_MAX_LENGTH_MESSAGE);

		Assert.isTrue(StringUtils.isNotBlank(vaccineInfoRequest.getHospitalPincode()),
				HOSPITAL_PINCODE_STATUS_MANDATORY);

		Assert.isTrue(StringUtils.isNotBlank(vaccineInfoRequest.getHospitalName()), HOSPITAL_NAME_MANDATORY);
		Assert.isTrue(vaccineInfoRequest.getHospitalName().length() <= MAX_ALLOWED_HOSPITAL_NAME_LENGTH,
				HOSPITAL_NAME_MAX_LENGTH_MESSAGE);

		if (isCreateVaccineRequest) {
			Assert.isTrue(vaccineInfoRequest.getMaxApplicableDoses() > 0, MAX_APPLICABLE_DOSES_MANDATORY);
			Assert.isTrue(vaccineInfoRequest.getMaxApplicableDoses() < 100, MAX_APPLICABLE_DOSES_LIMIT_EXCEED_MESSAGE);
			Assert.isTrue(vaccineInfoRequest.getMinDaysDifferenceNeeded() > 0, MIN_DIFFERENCE_NEEDED_MANDATORY);
			Assert.isTrue(vaccineInfoRequest.getMinDaysDifferenceNeeded() < 1000,
					MIN_DIFFERENCE_NEEDED_LIMIT_EXCEED_MESSAGE);
		}

		return true;
	}

}
