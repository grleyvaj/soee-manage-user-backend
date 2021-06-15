package es.soee.demo.application.shared.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Gloria R. Leyva Jerez
 */
public class OptionalIdValidator implements ConstraintValidator<OptionalId, Integer> {

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return id == null || id >= 0L;
    }
}
