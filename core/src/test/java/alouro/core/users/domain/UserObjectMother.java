package alouro.core.users.domain;

import alouro.shared.domain.Clock;

public final class UserObjectMother {

    public static User create(final String id, final String name, final String birthDate, final Clock clock) {
        return User.fromPrimitives(id, name, birthDate, clock);
    }

    public static User random(final Clock clock) {
        return create(
                UserIdObjectMother.random().value(),
                UserNameObjectMother.random().value(),
                UserBirthDateObjectMother.random(clock).value(),
                clock
        );
    }
}
