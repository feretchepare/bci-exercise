package com.globallogic.bci.exercise.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.globallogic.bci.exercise.model.Phone;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SignUpDto {
	private String name;
	@Email
	private String email;
	@NotBlank
//	@Pattern(regexp = "^[a-z0-9]{8,12}$",
//             message = "Username must be have at least 1 uppercase letter, only 2 numbers, and has to be between 8 and 12 characters long.")
	private String password;
	private Phone[] phones;

}
