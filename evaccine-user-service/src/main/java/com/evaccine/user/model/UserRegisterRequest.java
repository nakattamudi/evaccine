package com.evaccine.user.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserRegisterRequest {

    private String userName;
    private String aadharNumber;
    private String emailId;
    private String mobileNumber;
    private GenderType gender;
    private String countryCode;
}
