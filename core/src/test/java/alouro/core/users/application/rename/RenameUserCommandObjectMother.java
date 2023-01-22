package alouro.core.users.application.rename;

import alouro.core.users.domain.User;
import alouro.core.users.domain.UserIdObjectMother;
import alouro.core.users.domain.UserNameObjectMother;

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