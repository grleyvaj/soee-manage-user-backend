package es.soee.demo.interfaces.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModal implements Serializable {

    private static final long serialVersionUID = 2270062639915838735L;

    private String name;

    @Min(value = 18, message = "{min.age}")
    private Integer age;

    @Email(message = "{email.email}")
    private String email;
}