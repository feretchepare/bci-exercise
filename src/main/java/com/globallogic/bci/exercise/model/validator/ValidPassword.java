package com.globallogic.bci.exercise.model.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidPassword {
	String message() default "Username must be have at least 1 uppercase letter, only 2 numbers, and has to be between 8 and 12 characters long.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
