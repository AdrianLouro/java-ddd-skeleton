package alouro.core.users.application.create;

import alouro.core.users.domain.User;
import alouro.core.users.domain.UserBirthDateObjectMother;
import alouro.core.users.domain.UserIdObjectMother;
import alouro.core.users.domain.UserNameObjectMother;
import alouro.shared.domain.Clock;

public final class CreateUserCommandObjectMother {

    public static CreateUserCommand create(final String id, final String name, final String birthDate) {
        return new CreateUserCommand(id, name, birthDate);
    }

    public static CreateUserCommand random(final Clock clock) {
        return create(
                UserIdObjectMother.random().value(),
                UserNameObjectMother.random().value(),
                UserBirthDateObjectMother.random(clock).value()
        );
    }

    public static CreateUserCommand from(final User user) {
        return create(user.id().value(), user.name().value(), user.birthDate().value());
    }
}