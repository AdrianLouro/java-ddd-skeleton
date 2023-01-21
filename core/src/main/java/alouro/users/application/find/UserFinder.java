package alouro.users.application.find;

import alouro.users.application.UserResponse;
import alouro.users.domain.UserId;

public final class UserFinder {

    private final alouro.users.domain.UserFinder finder;

    public UserFinder(final alouro.users.domain.UserFinder finder) {
        this.finder = finder;
    }

    public UserResponse find(final UserId id) {
        return UserResponse.from(this.finder.find(id));
    }
}
