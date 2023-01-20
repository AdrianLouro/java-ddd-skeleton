package alouro.users.domain;

import alouro.domain.value_object.StringObjectMother;

public final class UserNameObjectMother {

    public static UserName create(final String value) {
        return new UserName(value);
    }

    public static UserName random() {
        return create(StringObjectMother.random()); // TODO: randomize with lengths between 2 and 32
    }
}
