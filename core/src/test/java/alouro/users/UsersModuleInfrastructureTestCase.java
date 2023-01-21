package alouro.users;

import alouro.InfrastructureTestCase;
import alouro.domain.Clock;
import alouro.domain.event.DomainEvent;
import alouro.domain.event.EventBus;
import alouro.infrastructure.SystemClock;
import alouro.users.application.create.UserCreator;
import alouro.users.application.rename.UserRenamer;
import alouro.users.domain.User;
import alouro.users.domain.UserRepository;
import alouro.users.infrastructure.InMemoryUserRepository;

import java.util.List;
import java.util.Optional;

public abstract class UsersModuleInfrastructureTestCase extends InfrastructureTestCase {
    protected final Clock clock = new SystemClock();

    protected final UserRepository userRepository = new InMemoryUserRepository();

    protected final EventBus eventBus = new EventBus() {
        @Override
        public void publish(List<DomainEvent> events) {

        }
    };

    protected final UserCreator userCreator = new UserCreator(this.userRepository, this.eventBus, this.clock);

    protected final UserRenamer userRenamer = new UserRenamer(this.userRepository, eventBus);

    protected final void save(final User user) {
        this.userRepository.save(user);
    }

    protected final Optional<User> search(final User user) {
        return this.userRepository.search(user.id());
    }
}
