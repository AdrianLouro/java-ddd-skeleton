package alouro.users;

import alouro.InfrastructureTestCase;
import alouro.domain.Clock;
import alouro.domain.DomainEvent;
import alouro.domain.DomainEventPublisher;
import alouro.infrastructure.SystemClock;
import alouro.users.application.UserCreator;
import alouro.users.application.UserRenamer;
import alouro.users.domain.User;
import alouro.users.domain.UserRepository;
import alouro.users.infrastructure.InMemoryUserRepository;

import java.util.List;
import java.util.Optional;

public abstract class UsersModuleInfrastructureTestCase extends InfrastructureTestCase {
    protected final Clock clock = new SystemClock();

    protected final UserRepository userRepository = new InMemoryUserRepository();

    protected final DomainEventPublisher domainEventPublisher = new DomainEventPublisher() {
        @Override
        public void publish(List<DomainEvent> events) {

        }
    };

    protected final UserCreator userCreator = new UserCreator(this.userRepository, this.domainEventPublisher, this.clock);

    protected final UserRenamer userRenamer = new UserRenamer(this.userRepository, domainEventPublisher);

    protected final void save(final User user) {
        this.userRepository.save(user);
    }

    protected final Optional<User> search(final User user) {
        return this.userRepository.search(user.id());
    }
}
