package com.evaccine.admin.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.evaccine.admin.model.BaseResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class AdminExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<BaseResponse> handleIllegalArguementException(IllegalArgumentException ex) {
		log.error("inside handleIllegalArguementException : {} ", ex.getMessage());
		BaseResponse baseResponse = prepareResponseErrorObject(ex.getMessage(), HttpStatus.BAD_REQUEST, null);
		return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<BaseResponse> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException ex) {
		log.error("inside handleMissingServletRequestParameterException : {} ", ex.getMessage());
		BaseResponse baseResponse = prepareResponseErrorObject(ex.getMessage(), HttpStatus.BAD_REQUEST, null);
		return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<BaseResponse> handleHttpMessageNotReadableException(InvalidFormatException ex) {
		log.error("inside handleHttpMessageNotReadableException : {} ", ex.getMessage());
		BaseResponse baseResponse = prepareResponseErrorObject(ex.getMessage(), HttpStatus.BAD_REQUEST, null);
		return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<BaseResponse> handleNullPointerException(NullPointerException ex) {
		log.error("inside handleNullPointerException : {} ", ex.getMessage());
		BaseResponse baseResponse = prepareResponseErrorObject(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		return new ResponseEntity<>(baseResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private BaseResponse prepareResponseErrorObject(String message, HttpStatus httpStatus, List<String> errorsList) {

		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setMessage(message);
		baseResponse.setHttpStatus(httpStatus);
		baseResponse.setStatusMessage(httpStatus.getReasonPhrase());
		if (!CollectionUtils.isEmpty(errorsList)) {
			baseResponse.setErrors(errorsList);
		}
		return baseResponse;
	}

}
