package alouro.core.users.application;

import alouro.core.users.domain.User;
import alouro.core.users.domain.UserBirthDateObjectMother;
import alouro.core.users.domain.UserIdObjectMother;
import alouro.core.users.domain.UserNameObjectMother;
import alouro.shared.domain.Clock;

public final class UserResponseObjectMother {

    public static UserResponse create(final String id, final String name, final String birthDate) {
        return new UserResponse(id, name, birthDate);
    }

    public static UserResponse random(final Clock clock) {
        return create(
                UserIdObjectMother.random().value(),
                UserNameObjectMother.random().value(),
                UserBirthDateObjectMother.random(clock).value()
        );
    }

    public static UserResponse from(final User user) {
        return create(user.id().value(), user.name().value(), user.birthDate().value());
    }
}
