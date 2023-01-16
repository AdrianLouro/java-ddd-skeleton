package alouro.users.domain;

import java.util.UUID;

public final class UserIdObjectMother {

    public static UserId create(final String value) {
        return new UserId(value);
    }

    public static UserId random() {
//        return UuidObjectMother.random(); // TODO: randomize
        return new UserId(UUID.randomUUID().toString());
    }
}
