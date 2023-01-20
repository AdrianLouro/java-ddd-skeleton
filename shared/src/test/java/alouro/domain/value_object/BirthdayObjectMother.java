package alouro.domain.value_object;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class BirthdayObjectMother {
    public static String random(final Integer minAge, final Integer maxAge) {
        final var date = ObjectMother.random().date().birthday(minAge, maxAge);

        return date
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
