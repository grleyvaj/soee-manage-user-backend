package es.soee.demo.domain.service;

import es.soee.demo.application.shared.exception.AlreadyExistsException;
import es.soee.demo.core.constant.URIConstant;
import es.soee.demo.domain.entity.User;
import es.soee.demo.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
@Component
public class UserFactory {

    private static final String USER = URIConstant.ENTITY_USER;

    private final IUserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserFactory(IUserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User createUserEntity(User userDomain) {
        if (!validateEmail(userDomain.getEmail()))
            throw new AlreadyExistsException(USER, "email", userDomain.getEmail());

        userDomain.setPassword(encoder.encode(userDomain.getPassword()));

        return userDomain;
    }

    public User updateUserEntity(User userToUpdate, User userData) {
        Optional<User> userByEmail = userRepository.findOneByEmail(userData.getEmail());

        if (userByEmail.isPresent() && userByEmail.get().getId() != userToUpdate.getId())
            throw new AlreadyExistsException(USER, "email", userToUpdate.getEmail());

        userToUpdate.setAge(userData.getAge());
        userToUpdate.setEmail(userData.getEmail());
        userToUpdate.setName(userData.getName());

        return userToUpdate;
    }

    public boolean validateEmail(String email) {
        return userRepository.findOneByEmail(email).isEmpty();
    }
}
