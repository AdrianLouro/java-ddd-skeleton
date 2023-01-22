package alouro.core.users.domain;

import alouro.shared.domain.value_object.UuidObjectMother;

public final class UserIdObjectMother {

    public static UserId create(final String value) {
        return new UserId(value);
    }

    public static UserId random() {
        return create(UuidObjectMother.random());
    }
}
