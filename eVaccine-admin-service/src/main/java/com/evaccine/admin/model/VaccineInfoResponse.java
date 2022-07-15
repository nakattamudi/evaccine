package com.evaccine.admin.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonInclude(value = Include.NON_EMPTY)
public class VaccineInfoResponse extends BaseResponse {

	private String vaccineName;
	private String maxApplicableDoses;
	private String minDaysDifferenceNeeded;
	private String hospitalName;
	private String hospitalPincode;
	private String countryCode;
	private StatusTypes hospitalStatus;

	private String createdBy;
	private String updatedBy;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}
