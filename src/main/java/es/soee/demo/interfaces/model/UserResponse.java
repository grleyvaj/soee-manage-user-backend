package es.soee.demo.interfaces.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

/**
 * @author Gloria R. Leyva Jerez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse extends RepresentationModel<UserResponse> implements Serializable {

    private static final long serialVersionUID = -5742038815398544245L;

    private long id;

    private String name;

    private int age;

    private String email;
}