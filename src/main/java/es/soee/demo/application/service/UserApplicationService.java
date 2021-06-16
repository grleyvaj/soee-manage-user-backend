package es.soee.demo.application.service;

import es.soee.demo.application.shared.exception.ResourceNotFoundException;
import es.soee.demo.core.constant.URIConstant;
import es.soee.demo.domain.entity.User;
import es.soee.demo.domain.repository.IUserRepository;
import es.soee.demo.domain.service.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static es.soee.demo.domain.service.UserSpecBuilder.criteriaTo;
import static es.soee.demo.domain.service.UserSpecBuilder.specsTo;

@Service
public class UserApplicationService {

    private static final String USER = URIConstant.ENTITY_USER;

    private final UserFactory userFactory;
    private final IUserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserApplicationService(UserFactory userFactory, IUserRepository userRepository, PasswordEncoder encoder) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User addUser(User user) {
        User userNew = userFactory.createUserEntity(user);
        return userRepository.save(userNew);
    }

    public User updateUser(long userId, User userData) {
        User userToUpdate = userRepository.findOneById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(USER, "id", userId));

        User userUpdated = userFactory.updateUserEntity(userToUpdate, userData);
        User userSaved = userRepository.save(userUpdated);
        return userSaved;
    }

    public void deleteUser(long userId) {
        if (userRepository.findOneById(userId).isPresent())
            userRepository.delete(userId);
    }

    public User getUser(long userId) {
        return userRepository.findOneById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(USER, "id", userId));
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User findByEmail(String email) {
        return userRepository.findOneByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + email));
    }

    public boolean updatePassword(String oldPassword, String newPassword, long userId) {
        User user = getUser(userId);
        if (!encoder.matches(oldPassword, user.getPassword())) {
            return false;
        } else {
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
    }

    public Page<User> getUsersByFilter(Pageable pageable, String name, Integer age, String email) {
        Specification<User> specs = specsTo(name, age, email);
        return userRepository.findAll(specs, pageable);
    }

    public Page<User> getUsersByFullText(Pageable pageable, String fullText) {
        Specification<User> specs = criteriaTo(fullText);
        return userRepository.findAll(specs, pageable);
    }

    public boolean existEmail(String email) {
        return userRepository.findOneByEmail(email).isPresent();
    }
}
