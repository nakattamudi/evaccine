package com.evaccine.admin.controller;

import static com.evaccine.admin.constants.AdminServiceConstants.ADMIN;
import static com.evaccine.admin.constants.AdminServiceConstants.DEACTIVATE_COUNTRY_INFO_MAPPING;
import static com.evaccine.admin.constants.AdminServiceConstants.DEACTIVATE_VACCINE_INFO_MAPPING;
import static com.evaccine.admin.constants.AdminServiceConstants.FETCH_COUNTRY_INFO_MAPPING;
import static com.evaccine.admin.constants.AdminServiceConstants.FETCH_VACCINE_INFO_MAPPING;
import static com.evaccine.admin.constants.AdminServiceConstants.REGISTER_COUNTRY_INFO_MAPPING;
import static com.evaccine.admin.constants.AdminServiceConstants.REGISTER_VACCINE_INFO_MAPPING;
import static com.evaccine.admin.constants.AdminServiceConstants.UPDATE_COUNTRY_INFO_MAPPING;
import static com.evaccine.admin.constants.AdminServiceConstants.UPDATE_VACCINE_INFO_MAPPING;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaccine.admin.model.CountryInfoRequest;
import com.evaccine.admin.model.CountryInfoResponse;
import com.evaccine.admin.model.VaccineInfoRequest;
import com.evaccine.admin.model.VaccineInfoResponse;
import com.evaccine.admin.service.AdminService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(ADMIN)
@Api(value = "adminservice", description = "CRUD Operations pertaining to admin services for vaccine and country entities")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping(REGISTER_COUNTRY_INFO_MAPPING)
	@ApiOperation(value = "Register Country Related Information", response = CountryInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
			@ApiResponse(responseCode = "403", description = "Access forbidden"),
			@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found") })
	public ResponseEntity<CountryInfoResponse> registerCountryInfo(
			@RequestBody final CountryInfoRequest countryInfoRequest) {
		log.info("registerCountryInfo Request : {} " + countryInfoRequest);
		CountryInfoResponse countryInfoResponse = adminService.registerCountryInfo(countryInfoRequest);
		return new ResponseEntity<>(countryInfoResponse, HttpStatus.OK);
	}

	@DeleteMapping(DEACTIVATE_COUNTRY_INFO_MAPPING)
	@ApiOperation(value = "Delete Country Related Information based on countryCode", response = CountryInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
			@ApiResponse(responseCode = "403", description = "Access forbidden"),
			@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found") })
	public ResponseEntity<CountryInfoResponse> deleteCountryInfo(@PathVariable final String countryCode) {
		log.info("deleteCountryInfo : {} " + countryCode);
		CountryInfoResponse countryInfoResponse = adminService.deleteCountryInfo(countryCode);
		return new ResponseEntity<>(countryInfoResponse, HttpStatus.OK);
	}

	@GetMapping(FETCH_COUNTRY_INFO_MAPPING)
	@ApiOperation(value = "Fetch Country Related Information based on countryCode", response = CountryInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
			@ApiResponse(responseCode = "403", description = "Access forbidden"),
			@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found") })
	public ResponseEntity<CountryInfoResponse> getCountryInfo(@PathVariable final String countryCode) {
		log.info("getCountryInfo : {}", countryCode);
		CountryInfoResponse countryInfoResponse = adminService.fetchCountryInfo(countryCode);
		return new ResponseEntity<>(countryInfoResponse, HttpStatus.OK);
	}

	@PutMapping(UPDATE_COUNTRY_INFO_MAPPING)
	@ApiOperation(value = "Update Country Related Information", response = CountryInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
			@ApiResponse(responseCode = "403", description = "Access forbidden"),
			@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found") })
	public ResponseEntity<CountryInfoResponse> updateCountryInfo(
			@RequestBody final CountryInfoRequest countryInfoRequest) {
		log.info("updateCountryInfo Request : {}", countryInfoRequest);
		CountryInfoResponse countryInfoResponse = adminService.updateCountryInfo(countryInfoRequest);
		return new ResponseEntity<>(countryInfoResponse, HttpStatus.OK);
	}

	@PostMapping(REGISTER_VACCINE_INFO_MAPPING)
	@ApiOperation(value = "Register Vaccine Related Information", response = VaccineInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
			@ApiResponse(responseCode = "403", description = "Access forbidden"),
			@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found") })
	public ResponseEntity<VaccineInfoResponse> registerVaccineInfo(
			@RequestBody final VaccineInfoRequest vaccineInfoRequest) {
		log.info("registerVaccineInfo Request : {} " + vaccineInfoRequest);
		VaccineInfoResponse vaccineInfoResponse = adminService.registerVaccineInfo(vaccineInfoRequest);
		return new ResponseEntity<>(vaccineInfoResponse, HttpStatus.OK);
	}

	@PutMapping(UPDATE_VACCINE_INFO_MAPPING)
	@ApiOperation(value = "Update Vaccine Related Information", response = VaccineInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
			@ApiResponse(responseCode = "403", description = "Access forbidden"),
			@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found") })
	public ResponseEntity<VaccineInfoResponse> updateVaccineInfo(
			@RequestBody final VaccineInfoRequest vaccineInfoRequest) {
		log.info("updateVaccineInfo Request : {} " + vaccineInfoRequest);
		VaccineInfoResponse vaccineInfoResponse = adminService.updateVaccineInfo(vaccineInfoRequest);
		return new ResponseEntity<>(vaccineInfoResponse, HttpStatus.OK);
	}

	@DeleteMapping(DEACTIVATE_VACCINE_INFO_MAPPING)
	@ApiOperation(value = "Delete Vaccine Related Information", response = VaccineInfoResponse.class)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
			@ApiResponse(responseCode = "403", description = "Access forbidden"),
			@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found") })
	public ResponseEntity<VaccineInfoResponse> deleteVaccineInfo(
			@RequestBody final VaccineInfoRequest vaccineInfoRequest) {
		log.info("deleteVaccineInfo Request : {} " + vaccineInfoRequest);
		VaccineInfoResponse vaccineInfoResponse = adminService.deleteVaccineInfo(vaccineInfoRequest);
		return new ResponseEntity<>(vaccineInfoResponse, HttpStatus.OK);
	}

	@GetMapping(FETCH_VACCINE_INFO_MAPPING)
	@ApiOperation(value = "Fetch Vaccine Related Information based on countryCode and pincode", response = List.class)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error"),
			@ApiResponse(responseCode = "400", description = "Bad Request"),
			@ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
			@ApiResponse(responseCode = "403", description = "Access forbidden"),
			@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found") })
	public ResponseEntity<List<VaccineInfoResponse>> getVaccineInfo(@PathVariable final String countryCode,
			@PathVariable final String pincode) {
		log.info("getVaccineInfo : {} , {}  ", countryCode, pincode);
		List<VaccineInfoResponse> countryInfoResponseList = adminService.fetchVaccineInfo(countryCode, pincode);
		return new ResponseEntity<>(countryInfoResponseList, HttpStatus.OK);
	}

}
