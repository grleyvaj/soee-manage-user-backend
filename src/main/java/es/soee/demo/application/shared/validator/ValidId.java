package es.soee.demo.application.shared.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author Gloria R. Leyva Jerez
 * This annotation validate a identifier
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidIdValidator.class)
public @interface ValidId {
    String message() default "Resource's identifier is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
