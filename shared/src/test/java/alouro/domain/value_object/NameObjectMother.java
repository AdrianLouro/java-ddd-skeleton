package alouro.domain.value_object;

public final class NameObjectMother {

    public static String randomFullName(final Integer maximumLength) {
        return ObjectMother.random().name().fullName().substring(0, maximumLength);
    }
}
