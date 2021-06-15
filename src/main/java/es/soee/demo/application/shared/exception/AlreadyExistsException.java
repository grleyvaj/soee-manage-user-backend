package es.soee.demo.application.shared.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 694012909758017640L;

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public AlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("AlreadyExist.%s.%s.%s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
