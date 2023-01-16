package alouro.domain.value_object;

import java.util.UUID;

public final class UuidObjectMother {
    public static String random() {
        return UUID.randomUUID().toString();
    }
}
