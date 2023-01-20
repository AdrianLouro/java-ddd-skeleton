package alouro.domain.value_object;

import com.github.javafaker.Faker;

public final class ObjectMother {
    private final static Faker faker = new Faker();

    public static Faker random() {
        return faker;
    }
}
