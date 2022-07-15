package com.evaccine.admin.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_EMPTY)
@Builder
public class CountryInfoRequest {

	private String countryName;
	private String countryCode;
	private StatusTypes countryStatus;
	private List<String> pincodes;

}
