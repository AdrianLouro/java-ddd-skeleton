package alouro.users.domain;


import alouro.domain.AggregateRoot;

import java.util.Objects;

public final class User extends AggregateRoot {

    private final UserId id;
    private UserName name;
    private UserBirthDate birthDate;

    public User(final UserId id, final UserName name, final UserBirthDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public static User create(final UserId id, final UserName name, final UserBirthDate birthDate) {
        var user = new User(id, name, birthDate);

        user.push(new UserCreatedDomainEvent(id.value(), name.value(), birthDate.value()));

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
        return super.hashCode();
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

    public void updateName(final UserName name) {
        if (this.name.value().equals(name.value())) {
            return;
        }

        this.name = name;

        this.push(new UserRenamedDomainEvent(this.id.value(), this.name.value()));
    }
}
