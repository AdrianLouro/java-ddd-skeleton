package alouro.core.users.application;

import alouro.core.users.domain.User;
import alouro.shared.domain.query.Response;

import java.util.Objects;

public final class UserResponse implements Response {
    private final String id;
    private final String name;
    private final String birthDate;

    public UserResponse(final String id, final String name, final String birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public static UserResponse fromAggregate(final User user) {
        return new UserResponse(user.id().value(), user.name().value(), user.birthDate().value());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate);
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String birthDate() {
        return birthDate;
    }
}
