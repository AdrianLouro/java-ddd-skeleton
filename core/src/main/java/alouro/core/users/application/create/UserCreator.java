package alouro.core.users.application.create;

import alouro.shared.domain.Clock;
import alouro.shared.domain.event.EventBus;
import alouro.core.users.domain.User;
import alouro.core.users.domain.UserRepository;

public final class UserCreator {
    private final UserRepository userRepository;
    private final EventBus eventBus;
    private final Clock clock;

    public UserCreator(
            final UserRepository userRepository,
            final EventBus eventBus,
            final Clock clock
    ) {
        this.userRepository = userRepository;
        this.eventBus = eventBus;
        this.clock = clock;
    }

    public void create(final String id, final String name, final String birthDate) {
        final var user = User.create(id, name, birthDate, this.clock);

        this.userRepository.save(user);

        this.eventBus.publish(user.pullDomainEvents());
    }
}
