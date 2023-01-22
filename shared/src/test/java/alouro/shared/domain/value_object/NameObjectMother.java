package alouro.shared.domain.value_object;

import static java.lang.Math.min;

public final class NameObjectMother {

    public static String randomFullName(final Integer maximumLength) {
        final var fullName = ObjectMother.random().name().fullName();

        return fullName.substring(0, min(fullName.length(), maximumLength));
    }
}
