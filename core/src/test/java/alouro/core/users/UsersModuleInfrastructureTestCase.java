package alouro.core.users;

import alouro.core.users.domain.User;
import alouro.core.users.domain.UserRepository;
import alouro.shared.InfrastructureTestCase;
import alouro.shared.domain.Clock;
import alouro.shared.domain.command.CommandBus;
import alouro.shared.domain.dependency_injection.Container;
import alouro.shared.domain.event.EventBus;
import alouro.shared.domain.query.QueryBus;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class UsersModuleInfrastructureTestCase extends InfrastructureTestCase {
    private Container container;
    private Clock clock;
    private UserRepository userRepository;
    private EventBus eventBus;
    private CommandBus commandBus;
    private QueryBus queryBus;

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

    protected final QueryBus queryBus() {
        if (this.queryBus == null) {
            this.queryBus = this.container().get(QueryBus.class);
        }

        return this.queryBus;
    }

    protected final void save(final User... users) {
        Stream.of(users).forEach(user -> this.userRepository().save(user));
    }

    protected final Optional<User> search(final User user) {
        return this.userRepository().search(user.id());
    }
}
