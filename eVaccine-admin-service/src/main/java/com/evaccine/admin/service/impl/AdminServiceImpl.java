package com.evaccine.admin.service.impl;

import static com.evaccine.admin.constants.AdminServiceConstants.ADMIN;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_CODE_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_DEACTIVATED_FAILURE_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_DEACTIVATED_SUCCESS_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_INFO_FETCH_FAILURE_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_REGISTER_SUCCESS_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_UPDATE_SUCCESS_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.PIN_CODE_MANDATORY;
import static com.evaccine.admin.constants.AdminServiceConstants.VACCINE_DEACTIVATED_FAILURE_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.VACCINE_DEACTIVATED_SUCCESS_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.VACCINE_REGISTER_SUCCESS_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.VACCINE_UPDATE_SUCCESS_MESSAGE;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.evaccine.admin.entity.CountryInfoEntity;
import com.evaccine.admin.entity.VaccineInfoEntity;
import com.evaccine.admin.model.CountryInfoRequest;
import com.evaccine.admin.model.CountryInfoResponse;
import com.evaccine.admin.model.StatusTypes;
import com.evaccine.admin.model.VaccineInfoRequest;
import com.evaccine.admin.model.VaccineInfoResponse;
import com.evaccine.admin.repository.AdminCountryInfoRepository;
import com.evaccine.admin.repository.AdminVaccineInfoRepository;
import com.evaccine.admin.service.AdminService;
import com.evaccine.admin.validator.AdminServiceValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminServiceValidator adminServiceValidator;

	@Autowired
	private AdminCountryInfoRepository adminCountryInfoRepository;

	@Autowired
	private AdminVaccineInfoRepository adminVaccineInfoRepository;

	@Override
	public CountryInfoResponse registerCountryInfo(final CountryInfoRequest countryInfoRequest) {
		log.info("registerCountryInfo Request Received :{}", countryInfoRequest);

		// validate countryInfo Request
		adminServiceValidator.validateCountryInfo(countryInfoRequest);

		// verify countryCode in DB and based on response save/update countryInfo
		saveOrUpdateCountryInfo(countryInfoRequest);
		CountryInfoResponse countryInfoResponse = new CountryInfoResponse();
		countryInfoResponse.setHttpStatus(HttpStatus.OK);
		countryInfoResponse.setMessage(COUNTRY_REGISTER_SUCCESS_MESSAGE);
		return countryInfoResponse;
	}

	@Override
	public CountryInfoResponse deleteCountryInfo(final String countryCode) {
		log.info("deleteCountryInfo Request Received for :{}", countryCode);
		Assert.isTrue(StringUtils.isNotBlank(countryCode), COUNTRY_CODE_MANDATORY);

		// verify countryCode in DB with ACTIVE status.If entry is available, then mark
		// Status as INACTIVE
		CountryInfoEntity countryInfoEntity = adminCountryInfoRepository.findByCountryCodeAndCountryStatus(countryCode,
				StatusTypes.ACTIVE);
		CountryInfoResponse countryInfoResponse = new CountryInfoResponse();

		if (null != countryInfoEntity) {
			countryInfoEntity.setUpdatedBy(ADMIN);
			countryInfoEntity.setCountryStatus(StatusTypes.INACTIVE);
			countryInfoEntity.setUpdatedDate(LocalDateTime.now());
			adminCountryInfoRepository.save(countryInfoEntity);
			countryInfoResponse.setMessage(COUNTRY_DEACTIVATED_SUCCESS_MESSAGE);
		} else {
			countryInfoResponse.setMessage(COUNTRY_DEACTIVATED_FAILURE_MESSAGE + countryCode);
		}
		countryInfoResponse.setHttpStatus(HttpStatus.OK);
		return countryInfoResponse;

	}

	@Override
	public CountryInfoResponse fetchCountryInfo(final String countryCode) {
		log.info("fetchCountryInfo Request Received for :{}", countryCode);

		Assert.isTrue(StringUtils.isNotBlank(countryCode), COUNTRY_CODE_MANDATORY);

		CountryInfoEntity countryInfoEntity = adminCountryInfoRepository.findByCountryCode(countryCode);
		CountryInfoResponse countryInfoResponse = new CountryInfoResponse();
		if (null != countryInfoEntity) {
			BeanUtils.copyProperties(countryInfoEntity, countryInfoResponse);
		} else {
			countryInfoResponse.setMessage(COUNTRY_INFO_FETCH_FAILURE_MESSAGE + countryCode);
			countryInfoResponse.setHttpStatus(HttpStatus.OK);
		}
		return countryInfoResponse;
	}

	@Override
	public CountryInfoResponse updateCountryInfo(final CountryInfoRequest countryInfoRequest) {
		log.info("updateCountryInfo Request Received :{}", countryInfoRequest);
		// validate countryInfo Request
		adminServiceValidator.validateCountryInfo(countryInfoRequest);

		// verify countryCode in DB and based on response save/update countryInfo
		saveOrUpdateCountryInfo(countryInfoRequest);
		CountryInfoResponse countryInfoResponse = new CountryInfoResponse();
		countryInfoResponse.setHttpStatus(HttpStatus.OK);
		countryInfoResponse.setMessage(COUNTRY_UPDATE_SUCCESS_MESSAGE);
		return countryInfoResponse;
	}

	@Override
	public VaccineInfoResponse registerVaccineInfo(final VaccineInfoRequest vaccineInfoRequest) {

		log.info("registerVaccineInfo Request Received :{}", vaccineInfoRequest);
		adminServiceValidator.validateVaccineInfo(vaccineInfoRequest, true);

		saveOrUpdateVaccineInfo(vaccineInfoRequest);
		VaccineInfoResponse vaccineInfoResponse = new VaccineInfoResponse();
		vaccineInfoResponse.setHttpStatus(HttpStatus.OK);
		vaccineInfoResponse.setMessage(VACCINE_REGISTER_SUCCESS_MESSAGE);
		return vaccineInfoResponse;
	}

	@Override
	public VaccineInfoResponse deleteVaccineInfo(final VaccineInfoRequest vaccineInfoRequest) {

		log.info("deleteVaccineInfo Request Received :{}", vaccineInfoRequest);
		adminServiceValidator.validateVaccineInfo(vaccineInfoRequest, false);

		VaccineInfoEntity vaccineInfoEntity = adminVaccineInfoRepository
				.findByVaccineNameAndHospitalNameAndHospitalPincodeAndCountryCodeAndHospitalStatus(
						vaccineInfoRequest.getVaccineName(), vaccineInfoRequest.getHospitalName(),
						vaccineInfoRequest.getHospitalPincode(), vaccineInfoRequest.getCountryCode(),
						StatusTypes.ACTIVE);

		VaccineInfoResponse vaccineInfoResponse = new VaccineInfoResponse();
		if (null != vaccineInfoEntity) {
			vaccineInfoEntity.setUpdatedBy(ADMIN);
			vaccineInfoEntity.setUpdatedDate(LocalDateTime.now());
			vaccineInfoEntity.setHospitalStatus(StatusTypes.INACTIVE);
			adminVaccineInfoRepository.save(vaccineInfoEntity);
			vaccineInfoResponse.setMessage(VACCINE_DEACTIVATED_SUCCESS_MESSAGE);
		} else {
			vaccineInfoResponse.setMessage(VACCINE_DEACTIVATED_FAILURE_MESSAGE);
		}
		vaccineInfoResponse.setHttpStatus(HttpStatus.OK);

		return vaccineInfoResponse;
	}

	@Override
	public List<VaccineInfoResponse> fetchVaccineInfo(final String countryCode, final String pincode) {

		log.info("fetchVaccineInfo Request Received for :{}", countryCode);
		Assert.isTrue(StringUtils.isNotBlank(countryCode), COUNTRY_CODE_MANDATORY);
		Assert.isTrue(StringUtils.isNotBlank(pincode), PIN_CODE_MANDATORY);

		List<VaccineInfoEntity> vaccineInfoEntityList = adminVaccineInfoRepository
				.findByHospitalPincodeAndCountryCode(pincode, countryCode);

		List<VaccineInfoResponse> vaccineInfoResponseList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(vaccineInfoEntityList)) {
			for (VaccineInfoEntity vaccineInfoEntity : vaccineInfoEntityList) {
				VaccineInfoResponse vaccineInfoResponse = new VaccineInfoResponse();
				BeanUtils.copyProperties(vaccineInfoEntity, vaccineInfoResponse);
				vaccineInfoResponseList.add(vaccineInfoResponse);
			}
		} else {
			log.info("No Details found for given countryCode {} and pincode {}:", countryCode, pincode);
		}
		return vaccineInfoResponseList;
	}

	@Override
	public VaccineInfoResponse updateVaccineInfo(final VaccineInfoRequest vaccineInfoRequest) {

		log.info("registerVaccineInfo Request Received :{}", vaccineInfoRequest);
		adminServiceValidator.validateVaccineInfo(vaccineInfoRequest, false);

		saveOrUpdateVaccineInfo(vaccineInfoRequest);
		VaccineInfoResponse vaccineInfoResponse = new VaccineInfoResponse();
		vaccineInfoResponse.setHttpStatus(HttpStatus.OK);
		vaccineInfoResponse.setMessage(VACCINE_UPDATE_SUCCESS_MESSAGE);
		return vaccineInfoResponse;
	}

	private void saveOrUpdateVaccineInfo(final VaccineInfoRequest vaccineInfoRequest) {

		log.info("saveOrUpdateVaccineInfo Request Received :{}", vaccineInfoRequest);
		VaccineInfoEntity vaccineInfoEntity = adminVaccineInfoRepository
				.findByVaccineNameAndHospitalNameAndHospitalPincodeAndCountryCode(vaccineInfoRequest.getVaccineName(),
						vaccineInfoRequest.getHospitalName(), vaccineInfoRequest.getHospitalPincode(),
						vaccineInfoRequest.getCountryCode());

		if (null != vaccineInfoEntity) {
			log.info("vaccineInfo exists for given Request : {} ", vaccineInfoRequest);
		} else {
			log.info("No vaccineInfo exists With given Details :{}.Creating a new entry", vaccineInfoRequest);
			vaccineInfoEntity = new VaccineInfoEntity();
			vaccineInfoEntity.setCreatedBy(ADMIN);
		}
		BeanUtils.copyProperties(vaccineInfoRequest, vaccineInfoEntity);
		vaccineInfoEntity.setUpdatedBy(ADMIN);
		vaccineInfoEntity.setUpdatedDate(LocalDateTime.now());
		adminVaccineInfoRepository.save(vaccineInfoEntity);
		log.info("saveOrUpdateVaccineInfo done succesfully ");
	}

	private void saveOrUpdateCountryInfo(final CountryInfoRequest countryInfoRequest) {

		log.info("saveOrUpdateCountryInfo Request Received :{}", countryInfoRequest);

		CountryInfoEntity countryInfoEntity = adminCountryInfoRepository
				.findByCountryCode(countryInfoRequest.getCountryCode());

		if (null != countryInfoEntity) {
			log.info("countryInfo exists for given countryCode : {} ", countryInfoRequest.getCountryCode());
		} else {
			log.info("No countryInfo exists for given countryCode : {}.Creating a new entry into DB ",
					countryInfoRequest.getCountryCode());
			countryInfoEntity = new CountryInfoEntity();
			countryInfoEntity.setCreatedBy(ADMIN);
		}
		BeanUtils.copyProperties(countryInfoRequest, countryInfoEntity);
		countryInfoEntity.setUpdatedBy(ADMIN);
		countryInfoEntity.setUpdatedDate(LocalDateTime.now());
		adminCountryInfoRepository.save(countryInfoEntity);
		log.info("saveOrUpdateCountryInfo done succesfully ");
	}

}
