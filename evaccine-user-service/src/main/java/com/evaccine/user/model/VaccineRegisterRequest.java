package com.evaccine.user.model;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@Builder
public class VaccineRegisterRequest {

    private String aadharNumber;
    private String vaccineName;
    private String hospitalName;
    private String hospitalPincode;
    private String countryCode;
    private int dosageNumber;
    private LocalDateTime vaccineRegistrationDate;
}
