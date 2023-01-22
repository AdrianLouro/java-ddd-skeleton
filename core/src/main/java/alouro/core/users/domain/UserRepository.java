package alouro.core.users.domain;

import java.util.Optional;

public interface UserRepository {
    void save(final User user);

    Optional<User> search(final UserId id);

//    List<User> matching(final Criteria criteria);
}
