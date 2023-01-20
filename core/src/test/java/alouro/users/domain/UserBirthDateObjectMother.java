package alouro.users.domain;

import alouro.domain.Clock;
import alouro.domain.value_object.BirthdayObjectMother;

public final class UserBirthDateObjectMother {
    public static UserBirthDate create(final String value, final Clock clock) {
        return new UserBirthDate(value, clock);
    }

    public static UserBirthDate random(final Clock clock) {
        return create(BirthdayObjectMother.random(18, 100), clock);
    }
}
