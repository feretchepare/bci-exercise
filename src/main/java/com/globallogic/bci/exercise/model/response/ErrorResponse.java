package com.globallogic.bci.exercise.model.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ErrorResponse {
	private List<ErrorResponseData> error;

	public ErrorResponse(ErrorResponseData newError) {
		if (this.error == null) {
			this.error = new ArrayList<>();
			this.error.add(newError);
		}
	}

}
