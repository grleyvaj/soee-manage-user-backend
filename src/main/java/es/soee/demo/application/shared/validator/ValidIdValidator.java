package es.soee.demo.application.shared.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Gloria R. Leyva Jerez
 */
public class ValidIdValidator implements ConstraintValidator<ValidId, String> {

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return id.matches("[0-9]*") && Long.parseLong(id) >= 1L;
    }
}
