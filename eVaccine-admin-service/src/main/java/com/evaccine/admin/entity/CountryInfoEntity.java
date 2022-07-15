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
@Table(name = "country_info")
@Data
public class CountryInfoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "country_name", nullable = false, unique = true)
	private String countryName;

	@Column(name = "country_code", nullable = false, unique = true)
	private String countryCode;

	@Column(name = "country_status", nullable = false)
	private StatusTypes countryStatus;

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
