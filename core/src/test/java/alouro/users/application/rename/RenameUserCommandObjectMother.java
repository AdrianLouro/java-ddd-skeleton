package alouro.users.application.rename;

import alouro.users.domain.User;
import alouro.users.domain.UserIdObjectMother;
import alouro.users.domain.UserNameObjectMother;

public final class RenameUserCommandObjectMother {

    public static RenameUserCommand create(final String id, final String name) {
        return new RenameUserCommand(id, name);
    }

    public static RenameUserCommand random() {
        return create(
                UserIdObjectMother.random().value(),
                UserNameObjectMother.random().value()
        );
    }

    public static RenameUserCommand from(final User user) {
        return create(user.id().value(), user.name().value());
    }
}