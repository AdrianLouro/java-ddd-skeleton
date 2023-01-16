package alouro.users.domain;

public final class UserNameObjectMother {

    public static UserName create(final String value) {
        return new UserName(value);
    }

    public static UserName random() {
//        return StringObjectMother.random(); // TODO: randomize
        return new UserName("John");
    }
}
