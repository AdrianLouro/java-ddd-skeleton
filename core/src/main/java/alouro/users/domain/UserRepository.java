package alouro.users.domain;

public interface UserRepository {
    User find(final UserId id);

    void save(final User user);
}
