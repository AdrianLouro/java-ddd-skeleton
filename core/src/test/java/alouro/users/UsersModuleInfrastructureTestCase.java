package alouro.users;

import alouro.domain.Clock;
import alouro.domain.DomainEvent;
import alouro.domain.DomainEventPublisher;
import alouro.infrastructure.SystemClock;
import alouro.users.application.UserCreator;
import alouro.users.application.UserRenamer;
import alouro.users.domain.User;
import alouro.users.infrastructure.InMemoryUserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.Random.class)
public abstract class UsersModuleInfrastructureTestCase {
    protected Clock clock = new SystemClock();
    protected InMemoryUserRepository userRepository = new InMemoryUserRepository();

    protected DomainEventPublisher domainEventPublisher = new DomainEventPublisher() {
        @Override
        public void publish(List<DomainEvent> events) {

        }
    };

    protected UserCreator userCreator = new UserCreator(this.userRepository, this.domainEventPublisher, this.clock);

    protected UserRenamer userRenamer = new UserRenamer(this.userRepository, domainEventPublisher);

    protected void persist(final User user) {
        this.userRepository.save(user);
    }

    protected Optional<User> search(final User user) {
        return this.userRepository.search(user.id());
    }
}
