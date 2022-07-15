package com.evaccine.admin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evaccine.admin.entity.CountryInfoEntity;
import com.evaccine.admin.model.StatusTypes;

@Repository
public interface AdminCountryInfoRepository extends CrudRepository<CountryInfoEntity, String> {

	CountryInfoEntity findByCountryCode(String countryName);

	CountryInfoEntity findByCountryCodeAndCountryStatus(String countryName, StatusTypes countryStatus);

}
