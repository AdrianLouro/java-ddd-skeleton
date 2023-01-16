package alouro.domain.value_object;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public final class StringObjectMother {
    public static String random() {
        final byte[] array = new byte[8]; // TODO: randomize
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}
