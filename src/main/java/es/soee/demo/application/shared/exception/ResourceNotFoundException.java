package es.soee.demo.application.shared.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6019553804512089739L;
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("NotFound.%s.%s.%s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
