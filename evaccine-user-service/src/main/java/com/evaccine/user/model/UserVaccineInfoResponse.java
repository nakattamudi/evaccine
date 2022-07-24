package com.evaccine.user.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"vaccineName", "hospitalName", "hospitalPincode", "countryCode"})
public class UserVaccineInfoResponse extends UserRegisterResponse {

    private String vaccineName;
    private String hospitalName;
    private String hospitalPincode;
}
