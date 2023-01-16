package alouro.users.domain;

import alouro.domain.Clock;

public final class UserObjectMother {

    public static User create(final UserId id, final UserName name, final UserBirthDate birthDate) {
        return new User(id, name, birthDate);
    }

    public static User random(Clock clock) {
        return create(UserIdObjectMother.random(), UserNameObjectMother.random(), UserBirthDateObjectMother.random(clock));
    }
}
