package com.evaccine.admin.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.evaccine.admin.entity.VaccineInfoEntity;
import com.evaccine.admin.model.StatusTypes;

public interface AdminVaccineInfoRepository extends CrudRepository<VaccineInfoEntity, String> {

	VaccineInfoEntity findByVaccineNameAndHospitalNameAndHospitalPincodeAndCountryCode(String vaccineName,
			String hospitalName, String pincode, String countryCode);

	VaccineInfoEntity findByVaccineNameAndHospitalNameAndHospitalPincodeAndCountryCodeAndHospitalStatus(
			String vaccineName, String hospitalName, String pincode, String countryCode, StatusTypes hospitalStatus);

	List<VaccineInfoEntity> findByHospitalPincodeAndCountryCode(String pincode, String countryCode);
}
