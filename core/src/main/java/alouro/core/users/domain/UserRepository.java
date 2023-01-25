package alouro.core.users.domain;

import alouro.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(final User user);

    Optional<User> search(final UserId id);

    List<User> matching(final Criteria criteria);
}
