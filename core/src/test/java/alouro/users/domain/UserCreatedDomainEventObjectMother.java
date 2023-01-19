package alouro.users.domain;

import alouro.domain.Clock;

public final class UserCreatedDomainEventObjectMother {
    public static UserCreatedDomainEvent create(final UserId id, final UserName name, final UserBirthDate birthDate) {
        return new UserCreatedDomainEvent(id.value(), name.value(), birthDate.value());
    }

    public static UserCreatedDomainEvent random(final Clock clock) {
        return create(
                UserIdObjectMother.random(),
                UserNameObjectMother.random(),
                UserBirthDateObjectMother.random(clock)
        );
    }

    public static UserCreatedDomainEvent from(final User user) {
        return create(user.id(), user.name(), user.birthDate());
    }
}
