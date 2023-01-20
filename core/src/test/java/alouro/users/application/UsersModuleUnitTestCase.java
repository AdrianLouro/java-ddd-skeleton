package alouro.users.application;

import alouro.domain.ClockMock;
import alouro.domain.DomainEventPublisherMock;
import alouro.users.domain.UserRepositoryMock;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
public abstract class UsersModuleUnitTestCase {
    private ClockMock clock;
    private DomainEventPublisherMock domainEventPublisher;
    private UserRepositoryMock userRepository;

    protected final ClockMock clock() {
        if (this.clock == null) {
            this.clock = new ClockMock();
        }

        return this.clock;
    }

    protected final DomainEventPublisherMock domainEventPublisher() {
        if (this.domainEventPublisher == null) {
            this.domainEventPublisher = new DomainEventPublisherMock();
        }

        return this.domainEventPublisher;
    }

    protected final UserRepositoryMock userRepository() {
        if (this.userRepository == null) {
            this.userRepository = new UserRepositoryMock();
        }

        return this.userRepository;
    }
}
