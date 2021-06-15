package es.soee.demo.infrastructure.persistence;

import es.soee.demo.domain.entity.User;
import es.soee.demo.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
@Service
public class UserJPARepositoryImp implements IUserRepository {

    private final IUserJPARepository userJPARepository;

    @Autowired
    public UserJPARepositoryImp(IUserJPARepository userJPARepository) {
        this.userJPARepository = userJPARepository;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userJPARepository.findAll(pageable);
    }

    @Override
    public User save(User user) {
        return userJPARepository.save(user);
    }

    @Override
    public Optional<User> findOneById(long userId) {
        return userJPARepository.findById(userId);
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return userJPARepository.findByEmail(email);
    }

    @Override
    public void delete(long userId) {
        userJPARepository.deleteById(userId);
    }

    @Override
    public Page<User> findAll(Specification<User> spec, Pageable pageable) {
        return userJPARepository.findAll(spec, pageable);
    }
}
