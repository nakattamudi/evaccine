package com.evaccine.admin.service;

import java.util.List;

import com.evaccine.admin.model.CountryInfoRequest;
import com.evaccine.admin.model.CountryInfoResponse;
import com.evaccine.admin.model.VaccineInfoRequest;
import com.evaccine.admin.model.VaccineInfoResponse;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    public CountryInfoResponse registerCountryInfo(CountryInfoRequest countryInfoRequest);

    public CountryInfoResponse deleteCountryInfo(String countryCode);

    public CountryInfoResponse fetchCountryInfo(String countryCode);

    public CountryInfoResponse updateCountryInfo(CountryInfoRequest countryInfoRequest);

    public VaccineInfoResponse registerVaccineInfo(VaccineInfoRequest vaccineInfoRequest);

    public VaccineInfoResponse deleteVaccineInfo(VaccineInfoRequest vaccineInfoRequest);

    public List<VaccineInfoResponse> fetchVaccineInfo(String countryCode, String pincode);

    public VaccineInfoResponse updateVaccineInfo(VaccineInfoRequest vaccineInfoRequest);
}
