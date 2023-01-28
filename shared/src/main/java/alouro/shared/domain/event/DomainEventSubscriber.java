package alouro.shared.domain.event;

public interface DomainEventSubscriber<T extends DomainEvent> {
    void dispatch(final T event);
}
