package alouro.users.application;

import alouro.domain.*;
import alouro.users.domain.*;

public final class UserRenamer {
    private final UserFinder userFinder;
    private final UserRepository userRepository;
    private final DomainEventPublisher domainEventPublisher;

    public UserRenamer(final UserRepository userRepository, final DomainEventPublisher domainEventPublisher) {
        /**
         * We can decide whether to inject or instantiate it
         *
         * If we instantiate like this, we assure that we test it correctly
         */
        this.userFinder = new UserFinder(userRepository);
        this.userRepository = userRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    public void rename(final UserId id, final UserName name) {
        final var user = this.userFinder.find(id);
        user.updateName(name);

        this.userRepository.save(user);

        this.domainEventPublisher.publish(user.pullDomainEvents());
    }
}
