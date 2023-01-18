package alouro.users.application;

import alouro.domain.Clock;
import alouro.domain.DomainEventPublisher;
import alouro.users.domain.User;
import alouro.users.domain.UserRepository;

public final class UserCreator {
    private final UserRepository userRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Clock clock;

    public UserCreator(
            final UserRepository userRepository,
            final DomainEventPublisher domainEventPublisher,
            final Clock clock
    ) {
        this.userRepository = userRepository;
        this.domainEventPublisher = domainEventPublisher;
        this.clock = clock;
    }

    public void create(final String id, final String name, final String birthDate) {
        final var user = User.create(id, name, birthDate, this.clock);

        this.userRepository.save(user);

        this.domainEventPublisher.publish(user.pullDomainEvents());
    }
}
