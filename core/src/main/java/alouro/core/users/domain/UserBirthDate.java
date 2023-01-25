package alouro.core.users.domain;


import alouro.shared.domain.Clock;
import alouro.shared.domain.value_object.Date;

import java.time.Period;

import static alouro.core.users.domain.User.MIN_AGE;

public final class UserBirthDate extends Date {
    public UserBirthDate(final String value, final Clock clock) {
        super(value);

        this.ensureIsValidUserBirthDate(clock);
    }

    private void ensureIsValidUserBirthDate(final Clock clock) {
        if (Period.between(this.localDate(), clock.now().toLocalDate()).getYears() < MIN_AGE) {
            throw new UserCannotBeUnderageException(this.value());
        }
    }
}
