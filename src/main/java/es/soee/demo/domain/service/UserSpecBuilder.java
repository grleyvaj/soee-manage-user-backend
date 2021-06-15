package es.soee.demo.domain.service;

import es.soee.demo.domain.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Gloria R. Leyva Jerez
 */
public class UserSpecBuilder {


    /**
     * Complex search
     *
     * @param name  user's name
     * @param age   user's age
     * @param email user's email
     * @return return Get the specifications for the User
     */
    public static Specification<User> specsTo(String name,
                                              Integer age,
                                              String email) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null) {
                /*predicates.add(cb.like(cb.function("unaccent", String.class, cb.lower(root.get("name"))),
                        "%" + toStripAccentsAndLowerCase(name) + "%"));*/
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (age != null) {
                predicates.add(cb.equal(root.get("age"), age));
            }

            if (email != null) {
                /*predicates.add(cb.like(cb.function("unaccent", String.class, cb.lower(root.get("email"))),
                        "%" + toStripAccentsAndLowerCase(email) + "%"));*/
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
            }

            Optional<Predicate> finalPredicate = predicates.stream().reduce(cb::and);
            return finalPredicate.orElse(null);
        };
    }

    /**
     * Full search
     *
     * @param fullText String to search in all fields
     * @return Get the specifications for the user
     */
    public static Specification<User> criteriaTo(String fullText) {
        return (root, query, cb) -> {

            System.out.println("text" + fullText);

            Predicate predicateName = cb.like(cb.lower(root.get("name")), "%" + fullText.toLowerCase() + "%");

            Predicate predicateAge = null;
            if (fullText.matches("[0-9]*")) {
                int age = Integer.parseInt(fullText);
                predicateAge = cb.equal(root.get("age"), age);
            }

            Predicate predicateEmail = cb.like(cb.lower(root.get("email")), "%" + fullText.toLowerCase() + "%");

            if (predicateAge != null)
                return cb.or(predicateName, predicateEmail, predicateAge);
            else
                return cb.or(predicateName, predicateEmail);
        };
    }

    public static String toStripAccentsAndLowerCase(String chain) {
        return StringUtils.stripAccents(chain).toLowerCase();
    }
}
