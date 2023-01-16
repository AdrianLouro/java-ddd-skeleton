package alouro.users.domain;

import alouro.domain.Clock;

public final class UserBirthDateObjectMother {
    public static UserBirthDate create(final String value, final Clock clock) {
        return new UserBirthDate(value, clock);
    }

    public static UserBirthDate random(final Clock clock) {
//        return create(DateObjectMother.random(), clock); // TODO: randomize
        return new UserBirthDate("2000-01-01", clock);
    }
}
