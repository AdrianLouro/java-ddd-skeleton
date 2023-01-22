package alouro.core.users.domain;


import alouro.shared.domain.AggregateRoot;
import alouro.shared.domain.Clock;

import java.util.Objects;

public final class User extends AggregateRoot {

    private final UserId id;
    private UserName name;
    private UserBirthDate birthDate;

    private User(final UserId id, final UserName name, final UserBirthDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public static User fromPrimitives(final String id, final String name, final String birthDate, final Clock clock) {
        return new User(
                new UserId(id),
                new UserName(name),
                new UserBirthDate(birthDate, clock)
        );
    }

    public static User create(final String id, final String name, final String birthDate, final Clock clock) {
        final var user = fromPrimitives(id, name, birthDate, clock);

        user.push(UserCreatedDomainEvent.from(user));

        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(birthDate, user.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate);
    }

    public UserId id() {
        return id;
    }

    public UserName name() {
        return name;
    }

    public UserBirthDate birthDate() {
        return birthDate;
    }

    public void renameWith(final UserName name) {
        if (this.name.value().equals(name.value())) {
            return;
        }

        this.name = name;

        this.push(UserRenamedDomainEvent.from(this));
    }
}