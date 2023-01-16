package alouro.domain;

import java.util.List;

public interface DomainEventPublisher {
    void publish(final List<DomainEvent> events);
}
