package alouro.core.users.application.find;

import alouro.core.users.domain.User;
import alouro.core.users.domain.UserIdObjectMother;

public final class FindUserQueryObjectMother {

    public static FindUserQuery create(final String id) {
        return new FindUserQuery(id);
    }

    public static FindUserQuery random() {
        return create(UserIdObjectMother.random().value());
    }

    public static FindUserQuery from(final User user) {
        return create(user.id().value());
    }
}