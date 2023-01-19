package alouro.users.domain;

public final class UserNameObjectMother {

    public static UserName create(final String value) {
        return new UserName(value);
    }

    public static UserName random() {
//        return StringObjectMother.random(); // TODO: randomize with lengths between 2 and 32
        return new UserName("John");
    }
}
