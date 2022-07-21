package com.evaccine.admin.controller;

import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_DEACTIVATED_SUCCESS_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.COUNTRY_REGISTER_SUCCESS_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.VACCINE_DEACTIVATED_SUCCESS_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.VACCINE_REGISTER_SUCCESS_MESSAGE;
import static com.evaccine.admin.constants.AdminServiceConstants.VACCINE_UPDATE_SUCCESS_MESSAGE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.evaccine.admin.TestUtil;
import com.evaccine.admin.constants.AdminServiceConstants;
import com.evaccine.admin.model.CountryInfoRequest;
import com.evaccine.admin.model.CountryInfoResponse;
import com.evaccine.admin.model.StatusTypes;
import com.evaccine.admin.model.VaccineInfoRequest;
import com.evaccine.admin.model.VaccineInfoResponse;
import com.evaccine.admin.service.AdminService;

@WebMvcTest
public class AdminServiceControllerTest {

	@MockBean
	private AdminService adminService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser
	public void registerCountryInfo() throws Exception {

		var requestBody = TestUtil.readFile("src/test/java/testData/validRegisterCountryInfoRequest.json");
		mockRegisterCountryInfoResponseForValidData();

		mockMvc.perform(post("/admin/register/country_info").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(String.valueOf(requestBody))).andExpect(status().is2xxSuccessful())
				.andExpect(content()
						.string("{\"message\":\"Country Details Registered Successfully\",\"httpStatus\":\"OK\"}"));
	}

	@Test
	@WithMockUser
	public void updateCountryInfo() throws Exception {
		var requestBody = TestUtil.readFile("src/test/java/testData/validRegisterCountryInfoRequest.json");
		mockUpdateCountryInfoResponseForValidData();

		mockMvc.perform(put("/admin/update/country_info").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(String.valueOf(requestBody))).andExpect(status().is2xxSuccessful())
				.andExpect(content()
						.string("{\"message\":\"Country Details Updated Successfully\",\"httpStatus\":\"OK\"}"));
	}

	@Test
	@WithMockUser
	public void deactivateCountryInfo() throws Exception {
		mockDeactivateCountryInfoResponseForValidData();
		mockMvc.perform(delete("/admin/deactivate/country/IND").with(csrf()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andExpect(
						content().string("{\"message\":\"Country Deactivated Successfully\",\"httpStatus\":\"OK\"}"));
	}

	@Test
	@WithMockUser
	public void fetchCountryInfo() throws Exception {
		mockFetchCountryInfoResponseForValidData();
		mockMvc.perform(get("/admin/fetch/country/IND").with(csrf())).andExpect(status().is2xxSuccessful())
				.andExpect(content().string("{\"httpStatus\":\"OK\",\"countryName\":\"INDIA\",\"countryCode\":\"IND\","
						+ "\"pincodes\":[\"123456\",\"232323\"],\"countryStatus\":\"ACTIVE\"}"));
	}

	@Test
	@WithMockUser
	public void registerVaccineInfo() throws Exception {
		var requestBody = TestUtil.readFile("src/test/java/testData/validRegisterVaccineInfoRequest.json");
		mockRegisterVaccineInfoResponseForValidData();

		mockMvc.perform(post("/admin/register/vaccine_info").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(String.valueOf(requestBody))).andExpect(status().is2xxSuccessful())
				.andExpect(content()
						.string("{\"message\":\"Vaccine Details Registered Successfully\",\"httpStatus\":\"OK\"}"));
	}

	@Test
	@WithMockUser
	public void updateVaccineInfo() throws Exception {
		var requestBody = TestUtil.readFile("src/test/java/testData/validRegisterVaccineInfoRequest.json");
		mockUpdateVaccineInfoResponseForValidData();

		mockMvc.perform(put("/admin/update/vaccine_info").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(String.valueOf(requestBody))).andExpect(status().is2xxSuccessful())
				.andExpect(content()
						.string("{\"message\":\"Vaccine Details Updated Successfully\",\"httpStatus\":\"OK\"}"));
	}

	@Test
	@WithMockUser
	public void deactivateVaccineInfo() throws Exception {
		var requestBody = TestUtil.readFile("src/test/java/testData/validRegisterVaccineInfoRequest.json");
		mockDeactivateVaccineInfoResponseForValidData();

		mockMvc.perform(delete("/admin/deactivate/vaccine_info").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(String.valueOf(requestBody))).andExpect(status().is2xxSuccessful())
				.andExpect(content()
						.string("{\"message\":\"Vaccine Details Deactivated Successfully\",\"httpStatus\":\"OK\"}"));
	}

	@Test
	@WithMockUser
	public void fetchVaccineInfo() throws Exception {
		mockFetchVaccineInfoResponseForValidData();
		mockMvc.perform(get("/admin/fetch/vaccine_info/IND/1233").with(csrf())).andExpect(status().is2xxSuccessful())
				.andExpect(content()
						.string("[{\"vaccineName\":\"COVAXIN\",\"hospitalName\":\"ICON\",\"hospitalPincode\":\"1233\","
								+ "\"countryCode\":\"IND\"},{\"vaccineName\":\"FIZER\",\"hospitalName\":\"ICON\"}]"));
	}

	private void mockRegisterCountryInfoResponseForValidData() {
		CountryInfoResponse countryInfoResponse = new CountryInfoResponse();
		countryInfoResponse.setHttpStatus(HttpStatus.OK);
		countryInfoResponse.setMessage(COUNTRY_REGISTER_SUCCESS_MESSAGE);
		when(adminService.registerCountryInfo(any(CountryInfoRequest.class))).thenReturn(countryInfoResponse);
	}

	private void mockUpdateCountryInfoResponseForValidData() {
		CountryInfoResponse countryInfoResponse = new CountryInfoResponse();
		countryInfoResponse.setHttpStatus(HttpStatus.OK);
		countryInfoResponse.setMessage(AdminServiceConstants.COUNTRY_UPDATE_SUCCESS_MESSAGE);
		when(adminService.updateCountryInfo(any(CountryInfoRequest.class))).thenReturn(countryInfoResponse);
	}

	private void mockRegisterVaccineInfoResponseForValidData() {
		VaccineInfoResponse vaccineInfoResponse = new VaccineInfoResponse();
		vaccineInfoResponse.setHttpStatus(HttpStatus.OK);
		vaccineInfoResponse.setMessage(VACCINE_REGISTER_SUCCESS_MESSAGE);
		when(adminService.registerVaccineInfo(any(VaccineInfoRequest.class))).thenReturn(vaccineInfoResponse);
	}

	private void mockUpdateVaccineInfoResponseForValidData() {
		VaccineInfoResponse vaccineInfoResponse = new VaccineInfoResponse();
		vaccineInfoResponse.setHttpStatus(HttpStatus.OK);
		vaccineInfoResponse.setMessage(VACCINE_UPDATE_SUCCESS_MESSAGE);
		when(adminService.updateVaccineInfo(any(VaccineInfoRequest.class))).thenReturn(vaccineInfoResponse);
	}

	private void mockDeactivateCountryInfoResponseForValidData() {
		CountryInfoResponse countryInfoResponse = new CountryInfoResponse();
		countryInfoResponse.setHttpStatus(HttpStatus.OK);
		countryInfoResponse.setMessage(COUNTRY_DEACTIVATED_SUCCESS_MESSAGE);
		when(adminService.deleteCountryInfo(any(String.class))).thenReturn(countryInfoResponse);
	}

	private void mockDeactivateVaccineInfoResponseForValidData() {
		VaccineInfoResponse vaccineInfoResponse = new VaccineInfoResponse();
		vaccineInfoResponse.setHttpStatus(HttpStatus.OK);
		vaccineInfoResponse.setMessage(VACCINE_DEACTIVATED_SUCCESS_MESSAGE);
		when(adminService.deleteVaccineInfo(any(VaccineInfoRequest.class))).thenReturn(vaccineInfoResponse);
	}

	private void mockFetchCountryInfoResponseForValidData() {
		CountryInfoResponse countryInfoResponse = new CountryInfoResponse();
		countryInfoResponse.setHttpStatus(HttpStatus.OK);
		countryInfoResponse.setCountryCode("IND");
		countryInfoResponse.setCountryName("INDIA");
		countryInfoResponse.setCountryStatus(StatusTypes.ACTIVE);
		countryInfoResponse.setPincodes(List.of("123456", "232323"));
		when(adminService.fetchCountryInfo(any(String.class))).thenReturn(countryInfoResponse);
	}

	private void mockFetchVaccineInfoResponseForValidData() {
		List<VaccineInfoResponse> vaccineInfoResponseList = new ArrayList<>();
		VaccineInfoResponse vaccineInfoResponse = new VaccineInfoResponse();
		vaccineInfoResponse.setCountryCode("IND");
		vaccineInfoResponse.setHospitalPincode("1233");
		vaccineInfoResponse.setVaccineName("COVAXIN");
		vaccineInfoResponse.setHospitalName("ICON");

		VaccineInfoResponse vaccineInfoResponse2 = new VaccineInfoResponse();
		vaccineInfoResponse.setCountryCode("IND");
		vaccineInfoResponse.setHospitalPincode("1233");
		vaccineInfoResponse2.setVaccineName("FIZER");
		vaccineInfoResponse2.setHospitalName("ICON");

		vaccineInfoResponseList.add(vaccineInfoResponse);
		vaccineInfoResponseList.add(vaccineInfoResponse2);
		when(adminService.fetchVaccineInfo(any(String.class), any(String.class))).thenReturn(vaccineInfoResponseList);
	}

}
