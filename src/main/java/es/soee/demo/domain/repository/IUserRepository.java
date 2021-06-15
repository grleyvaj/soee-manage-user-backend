package es.soee.demo.domain.repository;

import es.soee.demo.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
public interface IUserRepository {

    Page<User> findAll(Pageable pageable);

    User save(User user);

    Optional<User> findOneById(long userId);

    Optional<User> findOneByEmail(String email);

    void delete(long userId);

    Page<User> findAll(Specification<User> spec, Pageable pageable);
}
