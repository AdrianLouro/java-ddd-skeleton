package alouro.users.infrastructure;


import alouro.users.domain.User;
import alouro.users.domain.UserId;
import alouro.users.domain.UserRepository;

import java.util.ArrayList;
import java.util.List;

public final class InMemoryUserRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();

    @Override
    public User find(final UserId id) {
        return this.users.stream().filter(user -> user.id().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void save(final User user) {
        if (this.find(user.id()) != null) {
            return;
        }

        this.users.add(user);
    }
}
