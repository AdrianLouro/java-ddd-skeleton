package alouro;

import alouro.domain.ClockMock;
import alouro.domain.DomainEventPublisherMock;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
public abstract class UnitTestCase {
    private ClockMock clock;
    private DomainEventPublisherMock domainEventPublisher;

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
}
