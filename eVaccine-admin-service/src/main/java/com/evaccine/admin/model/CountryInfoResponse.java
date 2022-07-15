package com.evaccine.admin.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class CountryInfoResponse extends BaseResponse {

	private String countryName;
	private String countryCode;
	private List<String> pincodes;
	private StatusTypes countryStatus;

	private String createdBy;
	private String updatedBy;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

}
