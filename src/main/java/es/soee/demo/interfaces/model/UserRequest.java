package es.soee.demo.interfaces.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest implements Serializable {

    private static final long serialVersionUID = 2270062639915838735L;

    @NotBlank(message = "{notBlank.name}")
    private String name;

    @NotNull(message = "{notNull.age}")
    private int age;

    @NotBlank(message = "{notBlank.email}")
    @Email(message = "{email.email}")
    private String email;

    @Size(max = 60, message = "{size.password.60}")
    private String password;
}
