package alouro.users;

import alouro.InfrastructureTestCase;
import alouro.domain.Clock;
import alouro.domain.command.CommandBus;
import alouro.domain.dependency_injection.Container;
import alouro.domain.event.EventBus;
import alouro.users.domain.User;
import alouro.users.domain.UserRepository;

import java.util.Optional;

public abstract class UsersModuleInfrastructureTestCase extends InfrastructureTestCase {
    private Container container;
    private Clock clock;
    private UserRepository userRepository;
    private EventBus eventBus;
    private CommandBus commandBus;

    private Container container() {
        if (this.container == null) {
            this.container = new UsersModuleTestDependencyInjectionContainer();
        }

        return this.container;
    }

    protected final Clock clock() {
        if (this.clock == null) {
            this.clock = this.container().get(Clock.class);
        }

        return this.clock;
    }

    protected final UserRepository userRepository() {
        if (this.userRepository == null) {
            this.userRepository = this.container().get(UserRepository.class);
        }

        return this.userRepository;
    }

    protected final EventBus eventBus() {
        if (this.eventBus == null) {
            this.eventBus = this.container().get(EventBus.class);
        }

        return this.eventBus;
    }

    protected final CommandBus commandBus() {
        if (this.commandBus == null) {
            this.commandBus = this.container().get(CommandBus.class);
        }

        return this.commandBus;
    }

    protected final void save(final User user) {
        this.userRepository().save(user);
    }

    protected final Optional<User> search(final User user) {
        return this.userRepository().search(user.id());
    }
}
