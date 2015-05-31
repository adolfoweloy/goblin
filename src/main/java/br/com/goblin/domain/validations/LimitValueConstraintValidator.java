package br.com.goblin.domain.validations;

import java.math.BigDecimal;
import java.text.MessageFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LimitValueConstraintValidator implements ConstraintValidator<LimitValue, String> {

	private BigDecimal maxValue;
	
	@Override
	public void initialize(LimitValue annotation) {
		
		try {
			maxValue = validNumberOrZero(annotation.value());
		} catch (NumberFormatException e) {
			maxValue = BigDecimal.ONE;
		}
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		BigDecimal userValue = validNumberOrZero(value);
		
		if (userValue.compareTo(maxValue) > 0) {
			String template = context.getDefaultConstraintMessageTemplate();
			String message = MessageFormat.format(template, maxValue.toString());
			context.buildConstraintViolationWithTemplate(message)
				.addConstraintViolation()
				.disableDefaultConstraintViolation();
			
			return false;
		}
		
		return true;
	}

	private BigDecimal validNumberOrZero(String value) {
		try {
			return new BigDecimal(value);
		} catch (NumberFormatException e) {
			return BigDecimal.ONE;
		}
	}
}
