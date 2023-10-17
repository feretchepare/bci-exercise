package com.globallogic.bci.exercise.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidPassword {
	String message() default "Username must be have at least 1 uppercase letter, only 2 numbers, and has to be between 8 and 12 characters long.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
