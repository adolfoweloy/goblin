package br.com.goblin.domain.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy=LimitValueConstraintValidator.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface LimitValue {
	String message() default "The max value for this field is {0}";
	
	String value() default "0";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
