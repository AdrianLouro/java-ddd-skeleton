package alouro.users.application.rename;

import alouro.domain.event.EventBus;
import alouro.users.domain.*;

public final class UserRenamer {
    private final UserFinder userFinder;
    private final UserRepository userRepository;
    private final EventBus eventBus;

    public UserRenamer(final UserRepository userRepository, final EventBus eventBus) {
        /**
         * We can decide whether to inject or instantiate it
         * If we instantiate like this, we assure that we test it correctly
         */
        this.userFinder = new UserFinder(userRepository);
        this.userRepository = userRepository;
        this.eventBus = eventBus;
    }

    public void rename(final String id, final String name) {
        final var user = this.userFinder.find(new UserId(id));
        user.renameWith(new UserName(name)); // TODO: should the ValueObject be instantiated by the User ???

        this.userRepository.save(user);

        this.eventBus.publish(user.pullDomainEvents());
    }
}
