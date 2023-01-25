package alouro.core.users.domain;

import alouro.shared.domain.Clock;
import alouro.shared.domain.value_object.BirthdayObjectMother;

import static alouro.core.users.domain.UserObjectMother.MIN_AGE;
import static alouro.core.users.domain.UserObjectMother.MIN_ELDER_AGE;

public final class UserBirthDateObjectMother {
    public static UserBirthDate create(final String value, final Clock clock) {
        return new UserBirthDate(value, clock);
    }

    public static UserBirthDate random(final Clock clock) {
        return create(BirthdayObjectMother.random(MIN_AGE, 100), clock);
    }

    public static UserBirthDate elder(final Clock clock) {
        return create(BirthdayObjectMother.random(MIN_ELDER_AGE, 100), clock);
    }

    public static UserBirthDate notElder(final Clock clock) {
        return create(BirthdayObjectMother.random(MIN_AGE, MIN_ELDER_AGE - 1), clock);
    }
}
