package com.evaccine.admin.service.impl;

import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_DEACTIVATED_FAILURE_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_DEACTIVATED_SUCCESS_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_INFO_FETCH_FAILURE_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_REGISTER_SUCCESS_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_UPDATE_SUCCESS_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.VACCINE_DEACTIVATED_FAILURE_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.VACCINE_DEACTIVATED_SUCCESS_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.VACCINE_REGISTER_SUCCESS_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.VACCINE_UPDATE_SUCCESS_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.evaccine.admin.entity.CountryInfoEntity;
import com.evaccine.admin.entity.VaccineInfoEntity;
import com.evaccine.admin.model.CountryInfoRequest;
import com.evaccine.admin.model.CountryInfoResponse;
import com.evaccine.admin.model.StatusTypes;
import com.evaccine.admin.model.VaccineInfoRequest;
import com.evaccine.admin.model.VaccineInfoResponse;
import com.evaccine.admin.repository.AdminCountryInfoRepository;
import com.evaccine.admin.repository.AdminVaccineInfoRepository;
import com.evaccine.admin.validator.AdminServiceValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

@ExtendWith(MockitoExtension.class)
public class AdminServiceImplTest {

    @InjectMocks
    private AdminServiceImpl adminServiceImpl;

    @Mock
    private AdminServiceValidator adminServiceValidator;

    @Mock
    private AdminCountryInfoRepository adminCountryInfoRepository;

    @Mock
    private AdminVaccineInfoRepository adminVaccineInfoRepository;

    @Test
    public void registerCountryInfo() {

        CountryInfoRequest countryInfoRequest = new CountryInfoRequest();
        countryInfoRequest.setCountryCode("IND");
        countryInfoRequest.setCountryName("INDIA");
        countryInfoRequest.setCountryStatus(StatusTypes.ACTIVE);
        countryInfoRequest.setPincodes(List.of("34344", "34342", "3434343", "4232"));
        when(adminCountryInfoRepository.findByCountryCode(any(String.class))).thenReturn(null);
        CountryInfoResponse countryInfoResponse = adminServiceImpl.registerCountryInfo(countryInfoRequest);

        assertThat(countryInfoResponse).isNotNull();
        assertThat(countryInfoResponse.getMessage()).isNotNull();
        assertEquals(countryInfoResponse.getMessage(), COUNTRY_REGISTER_SUCCESS_MESSAGE);
        verify(adminCountryInfoRepository, times(1)).findByCountryCode(any(String.class));
        verify(adminCountryInfoRepository, times(1)).save(any(CountryInfoEntity.class));
    }

    @Test
    public void updateCountryInfo() {

        CountryInfoRequest countryInfoRequest = new CountryInfoRequest();
        countryInfoRequest.setCountryCode("IND");
        countryInfoRequest.setCountryName("INDIA");
        countryInfoRequest.setCountryStatus(StatusTypes.ACTIVE);
        countryInfoRequest.setPincodes(List.of("34344", "34342", "3434343", "4232"));

        CountryInfoEntity countryInfoEntity = new CountryInfoEntity();
        BeanUtils.copyProperties(countryInfoRequest, countryInfoEntity);
        when(adminCountryInfoRepository.findByCountryCode(any(String.class))).thenReturn(countryInfoEntity);
        CountryInfoResponse countryInfoResponse = adminServiceImpl.updateCountryInfo(countryInfoRequest);
        assertThat(countryInfoResponse).isNotNull();
        assertThat(countryInfoResponse.getMessage()).isNotNull();
        assertEquals(countryInfoResponse.getMessage(), COUNTRY_UPDATE_SUCCESS_MESSAGE);
        verify(adminCountryInfoRepository, times(1)).findByCountryCode(any(String.class));
        verify(adminCountryInfoRepository, times(1)).save(any(CountryInfoEntity.class));
    }

    @Test
    public void fetchCountryInfoWithExistingEntry() {

        CountryInfoEntity countryInfoEntity = new CountryInfoEntity();
        when(adminCountryInfoRepository.findByCountryCode(any(String.class))).thenReturn(countryInfoEntity);
        CountryInfoResponse countryInfoResponse = adminServiceImpl.fetchCountryInfo("ENG");
        assertThat(countryInfoResponse).isNotNull();
        assertThat(countryInfoResponse.getMessage()).isNull();
        verify(adminCountryInfoRepository, times(1)).findByCountryCode(any(String.class));
        verify(adminCountryInfoRepository, times(0)).save(any(CountryInfoEntity.class));
    }

    @Test
    public void fetchCountryInfoWithInvalidEntry() {

        String countryCode = "ENG";
        when(adminCountryInfoRepository.findByCountryCode(any(String.class))).thenReturn(null);
        CountryInfoResponse countryInfoResponse = adminServiceImpl.fetchCountryInfo(countryCode);
        assertThat(countryInfoResponse).isNotNull();
        assertThat(countryInfoResponse.getMessage()).isNotNull();
        assertEquals(countryInfoResponse.getMessage(), COUNTRY_INFO_FETCH_FAILURE_MESSAGE + countryCode);
        verify(adminCountryInfoRepository, times(1)).findByCountryCode(any(String.class));
        verify(adminCountryInfoRepository, times(0)).save(any(CountryInfoEntity.class));
    }

    @Test
    public void deleteCountryInfoWithValidEntry() {

        String countryCode = "ENG";
        CountryInfoEntity countryInfoEntity = new CountryInfoEntity();
        when(adminCountryInfoRepository.findByCountryCodeAndCountryStatus(any(String.class), any()))
                .thenReturn(countryInfoEntity);
        CountryInfoResponse countryInfoResponse = adminServiceImpl.deleteCountryInfo(countryCode);
        assertThat(countryInfoResponse).isNotNull();
        assertThat(countryInfoResponse.getMessage()).isNotNull();
        assertEquals(countryInfoResponse.getMessage(), COUNTRY_DEACTIVATED_SUCCESS_MESSAGE);
        verify(adminCountryInfoRepository, times(1)).findByCountryCodeAndCountryStatus(any(String.class), any());
        verify(adminCountryInfoRepository, times(1)).save(any(CountryInfoEntity.class));
    }

    @Test
    public void deleteCountryInfoWithInValidEntry() {

        String countryCode = "ENG";
        when(adminCountryInfoRepository.findByCountryCodeAndCountryStatus(any(String.class), any())).thenReturn(null);
        CountryInfoResponse countryInfoResponse = adminServiceImpl.deleteCountryInfo(countryCode);
        assertThat(countryInfoResponse).isNotNull();
        assertThat(countryInfoResponse.getMessage()).isNotNull();
        assertEquals(countryInfoResponse.getMessage(), COUNTRY_DEACTIVATED_FAILURE_MESSAGE + countryCode);
        verify(adminCountryInfoRepository, times(1)).findByCountryCodeAndCountryStatus(any(String.class), any());
        verify(adminCountryInfoRepository, times(0)).save(any(CountryInfoEntity.class));
    }

    @Test
    public void registerVaccineInfo() {

        VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();
        vaccineInfoRequest.setCountryCode("IND");
        vaccineInfoRequest.setVaccineName("COVAXIN");
        vaccineInfoRequest.setHospitalPincode("1234");
        vaccineInfoRequest.setHospitalStatus(StatusTypes.ACTIVE);
        vaccineInfoRequest.setMaxApplicableDoses(12);
        vaccineInfoRequest.setMinDaysDifferenceNeeded(300);
        vaccineInfoRequest.setHospitalName("ICON");

        when(adminVaccineInfoRepository.findByVaccineNameAndHospitalNameAndHospitalPincodeAndCountryCode(
                any(String.class), any(String.class), any(String.class), any(String.class))).thenReturn(null);
        VaccineInfoResponse vaccineInfoResponse = adminServiceImpl.registerVaccineInfo(vaccineInfoRequest);

        assertThat(vaccineInfoResponse).isNotNull();
        assertThat(vaccineInfoResponse.getMessage()).isNotNull();
        assertEquals(vaccineInfoResponse.getMessage(), VACCINE_REGISTER_SUCCESS_MESSAGE);
        verify(adminVaccineInfoRepository, times(1)).findByVaccineNameAndHospitalNameAndHospitalPincodeAndCountryCode(
                any(String.class), any(String.class), any(String.class), any(String.class));
        verify(adminVaccineInfoRepository, times(1)).save(any(VaccineInfoEntity.class));
    }

    @Test
    public void updateVaccineInfo() {

        VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();
        vaccineInfoRequest.setCountryCode("IND");
        vaccineInfoRequest.setVaccineName("COVAXIN");
        vaccineInfoRequest.setHospitalPincode("1234");
        vaccineInfoRequest.setHospitalStatus(StatusTypes.ACTIVE);
        vaccineInfoRequest.setMaxApplicableDoses(12);
        vaccineInfoRequest.setMinDaysDifferenceNeeded(300);
        vaccineInfoRequest.setHospitalName("ICON");
        VaccineInfoEntity vaccineInfoEntity = new VaccineInfoEntity();
        BeanUtils.copyProperties(vaccineInfoRequest, vaccineInfoEntity);
        when(adminVaccineInfoRepository.findByVaccineNameAndHospitalNameAndHospitalPincodeAndCountryCode(
                any(String.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(vaccineInfoEntity);
        VaccineInfoResponse vaccineInfoResponse = adminServiceImpl.updateVaccineInfo(vaccineInfoRequest);

        assertThat(vaccineInfoResponse).isNotNull();
        assertThat(vaccineInfoResponse.getMessage()).isNotNull();
        assertEquals(vaccineInfoResponse.getMessage(), VACCINE_UPDATE_SUCCESS_MESSAGE);
        verify(adminVaccineInfoRepository, times(1)).findByVaccineNameAndHospitalNameAndHospitalPincodeAndCountryCode(
                any(String.class), any(String.class), any(String.class), any(String.class));
        verify(adminVaccineInfoRepository, times(1)).save(any(VaccineInfoEntity.class));
    }

    @Test
    public void fetchVaccineInfoWithValidEntry() {

        String countryCode = "NZE";
        String pincode = "2133";
        VaccineInfoEntity vaccineInfoEntity = new VaccineInfoEntity();
        when(adminVaccineInfoRepository.findByHospitalPincodeAndCountryCode(any(String.class), any(String.class)))
                .thenReturn(List.of(vaccineInfoEntity));
        List<VaccineInfoResponse> vaccineInfoResponseList = adminServiceImpl.fetchVaccineInfo(countryCode, pincode);

        assertThat(vaccineInfoResponseList).isNotEmpty();
        verify(adminVaccineInfoRepository, times(1)).findByHospitalPincodeAndCountryCode(any(String.class),
                any(String.class));
        verify(adminVaccineInfoRepository, times(0)).save(any(VaccineInfoEntity.class));
    }

    @Test
    public void fetchVaccineInfoWithInValidEntry() {

        String countryCode = "NZE";
        String pincode = "2133";
        when(adminVaccineInfoRepository.findByHospitalPincodeAndCountryCode(any(String.class), any(String.class)))
                .thenReturn(null);
        List<VaccineInfoResponse> vaccineInfoResponseList = adminServiceImpl.fetchVaccineInfo(countryCode, pincode);

        assertThat(vaccineInfoResponseList).isEmpty();
        verify(adminVaccineInfoRepository, times(1)).findByHospitalPincodeAndCountryCode(any(String.class),
                any(String.class));
        verify(adminVaccineInfoRepository, times(0)).save(any(VaccineInfoEntity.class));
    }

    @Test
    public void deleteVaccineInfoWithInValidEntry() {

        String countryCode = "ENG";
        VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();
        vaccineInfoRequest.setCountryCode(countryCode);
        vaccineInfoRequest.setVaccineName("COVAXIN");
        vaccineInfoRequest.setHospitalPincode("1234");
        vaccineInfoRequest.setHospitalName("ICON");

        when(adminVaccineInfoRepository
                .findByVaccineNameAndHospitalNameAndHospitalPincodeAndCountryCodeAndHospitalStatus(any(String.class),
                        any(String.class), any(String.class), any(String.class), any())).thenReturn(null);
        VaccineInfoResponse vaccineInfoResponse = adminServiceImpl.deleteVaccineInfo(vaccineInfoRequest);
        assertThat(vaccineInfoResponse).isNotNull();
        assertThat(vaccineInfoResponse.getMessage()).isNotNull();
        assertEquals(vaccineInfoResponse.getMessage(), VACCINE_DEACTIVATED_FAILURE_MESSAGE);
        verify(adminVaccineInfoRepository, times(1))
                .findByVaccineNameAndHospitalNameAndHospitalPincodeAndCountryCodeAndHospitalStatus(any(String.class),
                        any(String.class), any(String.class), any(String.class), any());
        verify(adminVaccineInfoRepository, times(0)).save(any(VaccineInfoEntity.class));
    }

    @Test
    public void deleteVaccineInfoWithValidEntry() {

        String countryCode = "ENG";
        VaccineInfoRequest vaccineInfoRequest = new VaccineInfoRequest();
        vaccineInfoRequest.setCountryCode(countryCode);
        vaccineInfoRequest.setVaccineName("COVAXIN");
        vaccineInfoRequest.setHospitalPincode("1234");
        vaccineInfoRequest.setHospitalName("ICON");

        VaccineInfoEntity vaccineInfoEntity = new VaccineInfoEntity();
        when(adminVaccineInfoRepository
                .findByVaccineNameAndHospitalNameAndHospitalPincodeAndCountryCodeAndHospitalStatus(any(String.class),
                        any(String.class), any(String.class), any(String.class), any())).thenReturn(vaccineInfoEntity);
        VaccineInfoResponse vaccineInfoResponse = adminServiceImpl.deleteVaccineInfo(vaccineInfoRequest);
        assertThat(vaccineInfoResponse).isNotNull();
        assertThat(vaccineInfoResponse.getMessage()).isNotNull();
        assertEquals(vaccineInfoResponse.getMessage(), VACCINE_DEACTIVATED_SUCCESS_MESSAGE);
        verify(adminVaccineInfoRepository, times(1))
                .findByVaccineNameAndHospitalNameAndHospitalPincodeAndCountryCodeAndHospitalStatus(any(String.class),
                        any(String.class), any(String.class), any(String.class), any());
        verify(adminVaccineInfoRepository, times(1)).save(any(VaccineInfoEntity.class));
    }
}
