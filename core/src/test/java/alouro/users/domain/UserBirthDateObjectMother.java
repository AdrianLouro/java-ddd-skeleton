package alouro.users.domain;

import alouro.domain.Clock;
import alouro.domain.value_object.DateObjectMother;

public final class UserBirthDateObjectMother {
    public static UserBirthDate create(final String value, final Clock clock) {
        return new UserBirthDate(value, clock);
    }

    public static UserBirthDate random(final Clock clock) {
        return create(DateObjectMother.random(), clock); // TODO: randomize with periods inside [now - 18years, now]
    }
}
