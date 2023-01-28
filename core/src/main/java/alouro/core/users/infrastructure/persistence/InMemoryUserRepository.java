package alouro.core.users.infrastructure.persistence;

import alouro.core.users.domain.User;
import alouro.core.users.domain.UserId;
import alouro.core.users.domain.UserRepository;
import alouro.shared.domain.Clock;
import alouro.shared.domain.criteria.Criteria;
import alouro.shared.domain.criteria.Filters;
import alouro.shared.domain.criteria.Order;

import java.util.*;

/**
 * This should be a test fake, inside the test folder, instead of production code.
 * For now, we leave it here as production code in order to have an integration test example.
 */
public final class InMemoryUserRepository implements UserRepository {

    private final Clock clock;
    private final Map<String, User> users = new TreeMap<>();

    public InMemoryUserRepository(final Clock clock) {
        this.clock = clock;
    }

    @Override
    public void save(final User user) {
        this.search(user.id()).ifPresentOrElse(
                existingUser -> {
                },
                () -> this.users.put(user.id().value(), user)
        );
    }

    @Override
    public Optional<User> search(final UserId id) {
        return Optional.ofNullable(this.users.get(id.value()));
    }

    @Override
    public List<User> matching(final Criteria criteria) {
        if (criteria.equals(new Criteria(Filters.none(), Order.none(), null, 0))) {
            return new ArrayList<>(this.users.values());
        }

        return new ArrayList<>(this.users.values())
                .stream().filter(user -> user.isElder(this.clock)).toList(); // TODO: apply real criteria
    }
}
