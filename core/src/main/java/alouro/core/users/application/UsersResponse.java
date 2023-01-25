package alouro.core.users.application;

import alouro.shared.domain.query.Response;

import java.util.List;
import java.util.Objects;

public final class UsersResponse implements Response {
    private final List<UserResponse> users;

    public UsersResponse(final List<UserResponse> users) {
        this.users = users;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersResponse that = (UsersResponse) o;
        return users.equals(that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    public List<UserResponse> users() {
        return users;
    }
}
