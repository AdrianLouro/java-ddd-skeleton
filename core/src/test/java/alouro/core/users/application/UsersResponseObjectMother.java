package alouro.core.users.application;

import alouro.core.users.domain.User;

import java.util.List;

import static java.util.Collections.emptyList;

public final class UsersResponseObjectMother {

    public static UsersResponse create(final List<UserResponse> users) {
        return new UsersResponse(users);
    }

    public static UsersResponse from(final List<User> users) {
        return create(users.stream().map(UserResponseObjectMother::from).toList());
    }

    public static UsersResponse empty() {
        return create(emptyList());
    }
}
