package com.globallogic.bci.exercise.model.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

	// Rules: Lowercase letters + Just 1 uppercase letter + 2 numbers, between 8 and 12 characters.
	private static final String PASSWORD_PATTERN = "^(?=[^A-Z]*[A-Z][^A-Z]*$)(?=[^0-9]*[0-9]{2}[^0-9]*$)[a-zA-Z0-9]{8,12}$";

    @Override
	public boolean isValid(String pass, ConstraintValidatorContext context) {
		return (validatePassword(pass));
	}

	private boolean validatePassword(String pass) {
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(pass);
		return matcher.matches();
	}

}
