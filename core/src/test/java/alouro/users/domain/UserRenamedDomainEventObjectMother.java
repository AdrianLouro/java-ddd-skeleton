package alouro.users.domain;

public final class UserRenamedDomainEventObjectMother {
    public static UserRenamedDomainEvent create(final UserId id, final UserName name) {
        return new UserRenamedDomainEvent(id.value(), name.value());
    }

    public static UserRenamedDomainEvent random() {
        return create(
                UserIdObjectMother.random(),
                UserNameObjectMother.random()
        );
    }
}
