package es.soee.demo.interfaces.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginFormRequest implements Serializable {

    private static final long serialVersionUID = -5560251826998313584L;

    @NotBlank(message = "{notBlank.username}")
    @Email(message = "{email.username}")
    private String username;

    @NotBlank(message = "{notBlank.password}")
    @Size(max = 60, message = "{size.password.60}")
    private String password;
}
