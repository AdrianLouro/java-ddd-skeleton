package alouro.core.users.application.create;

import alouro.shared.domain.Clock;
import alouro.core.users.domain.*;

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