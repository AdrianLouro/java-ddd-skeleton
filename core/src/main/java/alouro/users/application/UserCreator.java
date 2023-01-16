package alouro.users.application;

import alouro.domain.*;
import alouro.users.domain.*;

public final class UserCreator {
    private final UserRepository userRepository;
    private final DomainEventPublisher domainEventPublisher;

    public UserCreator(final UserRepository userRepository, final DomainEventPublisher domainEventPublisher) {
        this.userRepository = userRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    public void create(final UserId id, final UserName name, final UserBirthDate birthDate) {
        final var user = User.create(id, name, birthDate);

        this.userRepository.save(user);

        this.domainEventPublisher.publish(user.pullDomainEvents());
    }
}
