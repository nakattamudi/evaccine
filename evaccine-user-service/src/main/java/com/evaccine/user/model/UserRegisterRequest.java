package com.evaccine.user.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    private String userName;
    private String aadharNumber;
    private String emailId;
    private String mobileNumber;
    private GenderType gender;
    private String countryCode;
}
