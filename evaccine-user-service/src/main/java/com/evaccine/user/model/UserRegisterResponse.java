package com.evaccine.user.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserRegisterResponse extends BaseResponse {

    private String userName;
    private String aadharNumber;
    private String emailId;
    private String mobileNumber;
    private GenderType gender;
    private String countryCode;

}
