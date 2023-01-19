package alouro.users.domain;

import alouro.domain.Clock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class UserBirthDateObjectMother {
    public static UserBirthDate create(final String value, final Clock clock) {
        return new UserBirthDate(value, clock);
    }

    public static UserBirthDate random(final Clock clock) {
//        return create(DateObjectMother.random(), clock); // TODO: randomize with periods inside [now - 18years, now]
        return new UserBirthDate(
                LocalDate.of(0, 1, 1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                clock
        );
    }
}
