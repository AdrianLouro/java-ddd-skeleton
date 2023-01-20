package alouro.users.domain;

import alouro.domain.value_object.NameObjectMother;

public final class UserNameObjectMother {

    public static UserName create(final String value) {
        return new UserName(value);
    }

    public static UserName random() {
        return create(NameObjectMother.randomFullName(32));
    }
}
