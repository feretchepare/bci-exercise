package com.globallogic.bci.exercise.model.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

	private Pattern pattern;
	private Matcher matcher;

	// Rule 1: Lowercase letters + digits, between 8 and 12 characters.
	private static final String PASSWORD_PATTERN = "^[a-z0-9]{8,12}$";

	@Override
	public void initialize(ValidPassword constraintAnnotation) {
	}

	@Override
	public boolean isValid(String pass, ConstraintValidatorContext context) {
		return (validatePassword(pass));
	}

	private boolean validatePassword(String pass) {
		pattern = Pattern.compile(PASSWORD_PATTERN);
		matcher = pattern.matcher(pass);
		// Rule 2: At least 2 numbers in any position.
		long digitCounter = pass.chars().filter(c -> Character.isDigit(c)).count();
		return matcher.matches() && digitCounter <= 2;
	}

}
