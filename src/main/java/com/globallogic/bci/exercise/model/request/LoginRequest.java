package com.globallogic.bci.exercise.model.request;

import lombok.Data;

@Data
public class LoginRequest {
	private String email;
	private String password;
}
