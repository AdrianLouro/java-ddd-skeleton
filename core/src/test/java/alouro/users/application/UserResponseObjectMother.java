package alouro.users.application;

import alouro.domain.Clock;
import alouro.users.domain.User;
import alouro.users.domain.UserBirthDateObjectMother;
import alouro.users.domain.UserIdObjectMother;
import alouro.users.domain.UserNameObjectMother;

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
