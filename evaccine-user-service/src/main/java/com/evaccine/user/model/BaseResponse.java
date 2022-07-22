package com.evaccine.user.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(value = Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

	@JsonInclude(value = Include.NON_EMPTY)
	private String message;
	@JsonInclude(value = Include.NON_EMPTY)
	private HttpStatus httpStatus;
	@JsonInclude(value = Include.NON_EMPTY)
	private String statusMessage;
	@JsonInclude(value = Include.NON_EMPTY)
	private List<String> errors;

}
