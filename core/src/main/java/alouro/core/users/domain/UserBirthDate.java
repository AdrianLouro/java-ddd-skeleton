package alouro.core.users.domain;


import alouro.shared.domain.Clock;
import alouro.shared.domain.value_object.Date;

import java.time.Period;

public final class UserBirthDate extends Date {
    public UserBirthDate(final String value, Clock clock) {
        super(value);

        this.ensureIsValidUserBirthDate(clock);
    }

    private void ensureIsValidUserBirthDate(Clock clock) {
        if (Period.between(this.localDate(), clock.now().toLocalDate()).getYears() < 18) {
            throw new UserCannotBeUnderageException();
        }
    }
}
