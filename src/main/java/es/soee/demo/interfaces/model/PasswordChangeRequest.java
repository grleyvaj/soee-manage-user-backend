package es.soee.demo.interfaces.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
public class PasswordChangeRequest implements Serializable {

    private static final long serialVersionUID = -1730153306754419417L;

    @NotBlank(message = "{notBlank.oldPassword}")
    @Size(max = 60, message = "{size.password.60}")
    @JsonProperty("old_password")
    private String oldPassword;

    @NotBlank(message = "{notBlank.newPassword}")
    @Size(max = 60, message = "{size.password.60}")
    @JsonProperty("new_password")
    private String newPassword;
}
