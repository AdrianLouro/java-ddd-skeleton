package alouro.users.domain;

import alouro.domain.Clock;

public final class UserCreatedDomainEventObjectMother {
    public static UserCreatedDomainEvent create(final String id, final String name, final String birthDate) {
        return new UserCreatedDomainEvent(id, name, birthDate);
    }

    public static UserCreatedDomainEvent random(final Clock clock) {
        return create(
                UserIdObjectMother.random().value(),
                UserNameObjectMother.random().value(),
                UserBirthDateObjectMother.random(clock).value()
        );
    }

    public static UserCreatedDomainEvent from(final User user) {
        return create(user.id().value(), user.name().value(), user.birthDate().value());
    }
}
