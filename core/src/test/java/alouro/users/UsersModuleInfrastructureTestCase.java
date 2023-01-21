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
    protected final Container container = new UsersModuleTestDependencyInjectionContainer();

    protected final Clock clock = this.container.get(Clock.class);

    protected final UserRepository userRepository = this.container.get(UserRepository.class);

    protected final EventBus eventBus = this.container.get(EventBus.class);

    protected final CommandBus commandBus = this.container.get(CommandBus.class);

    protected final void save(final User user) {
        this.userRepository.save(user);
    }

    protected final Optional<User> search(final User user) {
        return this.userRepository.search(user.id());
    }
}
