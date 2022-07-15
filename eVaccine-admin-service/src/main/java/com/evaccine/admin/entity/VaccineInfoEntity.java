package com.evaccine.admin.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.evaccine.admin.model.StatusTypes;

import lombok.Data;

@Entity
@Table(name = "Vaccine_info")
@Data
public class VaccineInfoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "vaccine_name", nullable = false)
	private String vaccineName;

	@Column(name = "max_applicable_doses", nullable = false)
	private int maxApplicableDoses;

	@Column(name = "min_days_diff_needed", nullable = false)
	private int minDaysDifferenceNeeded;

	@Column(name = "hospital_name", nullable = false)
	private String hospitalName;

	@Column(name = "hospital_pincode", nullable = false)
	private String hospitalPincode;

	@Column(name = "country_code", nullable = false)
	private String countryCode;

	@Column(name = "hospital_status", nullable = false)
	private StatusTypes hospitalStatus;

	@CreatedBy
	@Column(name = "created_by", nullable = false, updatable = false)
	private String createdBy;

	@Column(name = "updated_by", nullable = false)
	@LastModifiedBy
	private String updatedBy;

	@Column(name = "created_date", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdDate = LocalDateTime.now();

	@Column(name = "updated_date", nullable = false)
	@LastModifiedDate
	private LocalDateTime updatedDate;

}
