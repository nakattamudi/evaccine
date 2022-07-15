package com.evaccine.admin.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonInclude(value = Include.NON_EMPTY)
public class VaccineInfoRequest {

	private String vaccineName;
	private int maxApplicableDoses;
	private int minDaysDifferenceNeeded;
	private String hospitalName;
	private String hospitalPincode;
	private String countryCode;
	private StatusTypes hospitalStatus;

}
