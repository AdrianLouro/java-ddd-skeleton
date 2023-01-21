package alouro.users.application.find;

import alouro.users.application.UserResponse;
import alouro.users.domain.UserId;

public final class UserFinder {

    private final alouro.users.domain.UserFinder userFinder;

    public UserFinder(final alouro.users.domain.UserFinder userFinder) {
        this.userFinder = userFinder;
    }

    public UserResponse find(final UserId id) {
        return UserResponse.from(this.userFinder.find(id));
    }
}
