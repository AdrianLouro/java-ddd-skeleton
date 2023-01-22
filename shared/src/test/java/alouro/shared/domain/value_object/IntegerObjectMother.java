package alouro.shared.domain.value_object;

public final class IntegerObjectMother {
    public static Integer random(final Integer min, final Integer max) {
        return ObjectMother.random().number().numberBetween(min, max);
    }
}
