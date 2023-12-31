package com.globallogic.bci.exercise.exceptions;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.globallogic.bci.exercise.model.response.ErrorResponse;
import com.globallogic.bci.exercise.model.response.ErrorResponseData;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private ErrorResponse errors;

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		String errorString = null;
		try {
			errorString = ex.getMessage();
			if (errorString == null) {
				errorString = ex.getCause().toString();
				if (errorString == null) {
					errorString = ex.toString();
				}
			}
		} catch (Exception exGettingString) {
			errorString = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
		}

		ErrorResponseData error = new ErrorResponseData(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorString);
		errors = new ErrorResponse(error);
		log.error("EXCEPTION: " + errorString);
		return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = { EntityExistsException.class })
	public ResponseEntity<Object> handleEntityExistsException(Exception ex, WebRequest request) {

		String errorString = ex.getLocalizedMessage();

		if (errorString == null) {
			errorString = ex.toString();
		}

		ErrorResponseData error = new ErrorResponseData(HttpStatus.CONFLICT.value(), errorString);
		errors = new ErrorResponse(error);
		log.error("EXCEPTION: " + errorString);
		return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = { BadCredentialsException.class })
	public ResponseEntity<Object> handleBadCredentialsException(Exception ex, WebRequest request) {

		String errorString = ex.getLocalizedMessage();

		if (errorString == null) {
			errorString = ex.toString();
		}

		ErrorResponseData error = new ErrorResponseData(HttpStatus.BAD_REQUEST.value(), errorString);
		errors = new ErrorResponse(error);
		log.error("EXCEPTION: " + errorString);
		return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
