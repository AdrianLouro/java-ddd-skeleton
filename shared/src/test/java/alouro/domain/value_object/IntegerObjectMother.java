package alouro.domain.value_object;

import java.util.Random;

public final class IntegerObjectMother {
    public static Integer random() {
        return new Random().nextInt(); // TODO: randomize
    }
}
