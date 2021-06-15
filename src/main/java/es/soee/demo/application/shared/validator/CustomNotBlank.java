package es.soee.demo.application.shared.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author Gloria R. Leyva Jerez
 * This annotation permit null field but not blank.
 * If exists a string, this string can't can't be blank
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomNotBlankValidator.class)
public @interface CustomNotBlank {
    String message() default "This fields can't be blank";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
