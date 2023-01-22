package alouro.core.users.domain;

import alouro.shared.domain.Clock;

public final class UserBuilder {

    private final Clock clock;
    private String id;
    private String name;
    private String birthDate;

    private UserBuilder(final String id, final String name, final String birthDate, final Clock clock) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.clock = clock;
    }

    public static UserBuilder random(final Clock clock) {
        return new UserBuilder(
                UserIdObjectMother.random().value(),
                UserNameObjectMother.random().value(),
                UserBirthDateObjectMother.random(clock).value(),
                clock
        );
    }

    public static UserBuilder fromUser(final User user, final Clock clock) {

        return new UserBuilder(
                user.id().value(),
                user.name().value(),
                user.birthDate().value(),
                clock
        );
    }

    public UserBuilder withId(final String id) {
        this.id = id;

        return this;
    }

    public UserBuilder withName(final String name) {
        this.name = name;

        return this;
    }

    public UserBuilder withBirthDate(final String birthDate) {
        this.birthDate = birthDate;

        return this;
    }

    public User build() {
        return User.fromPrimitives(this.id, this.name, this.birthDate, this.clock);
    }
}
