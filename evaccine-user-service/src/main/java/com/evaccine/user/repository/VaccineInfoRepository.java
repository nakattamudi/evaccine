package com.evaccine.user.repository;

import java.util.List;

import com.evaccine.user.entity.VaccineInfoEntity;
import com.evaccine.user.model.StatusTypes;
import org.springframework.data.repository.CrudRepository;

public interface VaccineInfoRepository extends CrudRepository<VaccineInfoEntity, String> {

    VaccineInfoEntity findByVaccineNameAndHospitalNameAndHospitalPincodeAndCountryCode(String vaccineName,
                                                                                       String hospitalName,
                                                                                       String pincode,
                                                                                       String countryCode);

    VaccineInfoEntity findByVaccineNameAndHospitalNameAndHospitalPincodeAndCountryCodeAndHospitalStatus(
            String vaccineName, String hospitalName, String pincode, String countryCode, StatusTypes hospitalStatus);

    List<VaccineInfoEntity> findByHospitalPincodeAndCountryCode(String pincode, String countryCode);
}
