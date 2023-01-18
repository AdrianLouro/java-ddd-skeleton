package alouro.users;

import alouro.domain.Clock;
import alouro.domain.DomainEvent;
import alouro.domain.DomainEventPublisher;
import alouro.infrastructure.SystemClock;
import alouro.users.application.UserCreator;
import alouro.users.application.UserRenamer;
import alouro.users.domain.User;
import alouro.users.infrastructure.InMemoryUserRepository;

import java.util.List;

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

    protected User find(final User user) {
        return this.userRepository.find(user.id());
    }
}