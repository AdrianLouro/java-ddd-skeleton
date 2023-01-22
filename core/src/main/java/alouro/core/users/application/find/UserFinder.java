package alouro.core.users.application.find;

import alouro.core.users.application.UserResponse;
import alouro.core.users.domain.UserId;

public final class UserFinder {

    private final alouro.core.users.domain.UserFinder finder;

    public UserFinder(final alouro.core.users.domain.UserFinder finder) {
        this.finder = finder;
    }

    public UserResponse find(final UserId id) {
        return UserResponse.from(this.finder.find(id));
    }
}
