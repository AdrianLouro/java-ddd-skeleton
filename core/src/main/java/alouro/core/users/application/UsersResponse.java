package alouro.core.users.application;

import alouro.shared.domain.query.Response;

import java.util.List;

public final class UsersResponse implements Response {
    private final List<UserResponse> users;

    public UsersResponse(final List<UserResponse> users) {
        this.users = users;
    }

    public List<UserResponse> users() {
        return users;
    }
}
