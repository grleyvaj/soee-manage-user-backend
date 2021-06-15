package es.soee.demo.interfaces.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangeResponse implements Serializable {

    private static final long serialVersionUID = 8425466743720980785L;

    private Boolean valid;

    private String message;
}
