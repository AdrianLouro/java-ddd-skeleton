package alouro.users.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<User> search(final UserId id);

    void save(final User user);
}
