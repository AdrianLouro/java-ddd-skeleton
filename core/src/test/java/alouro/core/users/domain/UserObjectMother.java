package alouro.core.users.domain;

import alouro.shared.domain.Clock;

public final class UserObjectMother {
    public static final Integer MIN_ELDER_AGE = 65;
    public static final Integer MIN_AGE = 18;

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

    public static User elder(final Clock clock) {
        return UserBuilder
                .random(clock)
                .withBirthDate(UserBirthDateObjectMother.elder(clock).value())
                .build();
    }

    public static User notElder(final Clock clock) {
        return UserBuilder
                .random(clock)
                .withBirthDate(UserBirthDateObjectMother.notElder(clock).value())
                .build();
    }
}
