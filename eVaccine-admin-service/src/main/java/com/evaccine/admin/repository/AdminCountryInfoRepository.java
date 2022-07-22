package com.evaccine.admin.repository;

import com.evaccine.admin.entity.CountryInfoEntity;
import com.evaccine.admin.model.StatusTypes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminCountryInfoRepository extends CrudRepository<CountryInfoEntity, String> {

    CountryInfoEntity findByCountryCode(String countryName);

    CountryInfoEntity findByCountryCodeAndCountryStatus(String countryName, StatusTypes countryStatus);

}
