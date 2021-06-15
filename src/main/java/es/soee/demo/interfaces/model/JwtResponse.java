package es.soee.demo.interfaces.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@Builder
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -378180746614962438L;

    private String token;

    private String type = "Bearer";

    private String email;

    private Collection<? extends GrantedAuthority> authorities;
}
