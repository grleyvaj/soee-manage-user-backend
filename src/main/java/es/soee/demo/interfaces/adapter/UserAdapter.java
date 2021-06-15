package es.soee.demo.interfaces.adapter;

import es.soee.demo.domain.entity.User;
import es.soee.demo.interfaces.model.UserRequest;
import es.soee.demo.interfaces.model.UserResponse;
import org.springframework.stereotype.Component;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class UserAdapter {

    public static User mapToDomain(UserRequest request) {
        return User.builder()
                .name(request.getName())
                .age(request.getAge())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    public UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .email(user.getEmail())
                .build();
    }
}
