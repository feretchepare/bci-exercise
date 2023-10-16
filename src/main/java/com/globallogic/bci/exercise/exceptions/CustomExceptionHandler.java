package com.globallogic.bci.exercise.exceptions;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.globallogic.bci.exercise.model.response.ErrorResponse;
import com.globallogic.bci.exercise.model.response.ErrorResponseData;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private ErrorResponse errors;

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {

		String errorString = ex.getCause().toString();

		if (errorString == null) {
			errorString = ex.toString();
		}

		ErrorResponseData error = new ErrorResponseData(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorString);
		errors = new ErrorResponse(error);
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
		return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.CONFLICT);
	}
}
