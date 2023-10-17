package com.globallogic.bci.exercise.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.globallogic.bci.exercise.model.Phone;
import com.globallogic.bci.exercise.model.validator.ValidEmail;
import com.globallogic.bci.exercise.model.validator.ValidPassword;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SignUpDto {
	private String name;
	@ValidEmail
	@NotNull
	@NotEmpty
	private String email;
	@ValidPassword
	@NotNull
	@NotBlank
	private String password;
	private Phone[] phones;

}
