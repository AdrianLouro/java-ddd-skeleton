package alouro.users.infrastructure;

import alouro.users.domain.User;
import alouro.users.domain.UserId;
import alouro.users.domain.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * This should be a test fake, inside the test folder, instead of production code.
 * For now, we leave it here as production code in order to have an integration test example.
 */
public final class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public void save(final User user) {
        if (this.search(user.id()).isPresent()) {
            return;
        }

        this.users.put(user.id().value(), user);
    }

    @Override
    public Optional<User> search(final UserId id) {
        return Optional.ofNullable(this.users.get(id.value()));
    }
}
