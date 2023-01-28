package alouro.shared.infrastructure.event;

import alouro.shared.domain.dependency_injection.Container;
import alouro.shared.domain.event.DomainEvent;
import alouro.shared.domain.event.DomainEventSubscriber;
import alouro.shared.domain.event.EventBus;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class InMemoryEventBus implements EventBus {

    private final Container container;
    private final Map<Class<? extends DomainEvent>, List<Class<? extends DomainEventSubscriber>>> subscriptions;

    public InMemoryEventBus(
            final Container container,
            final Map<Class<? extends DomainEvent>, List<Class<? extends DomainEventSubscriber>>> subscriptions
    ) {
        this.container = container;
        this.subscriptions = subscriptions;
    }

    @Override
    public void publish(final List<DomainEvent> events) {
        events.forEach(this::dispatchEvent);
    }

    private void dispatchEvent(final DomainEvent event) {
        this.getSubscribersFor(event).ifPresent(
                eventSubscribers ->
                        eventSubscribers.forEach(eventSubscriber -> this.dispatchEvent(event, eventSubscriber))
        );
    }

    private Optional<List<Class<? extends DomainEventSubscriber>>> getSubscribersFor(final DomainEvent event) {
        return Optional.ofNullable(this.subscriptions.get(event.getClass()));
    }

    private void dispatchEvent(final DomainEvent event, final Class<? extends DomainEventSubscriber> eventSubscriberClass) {
        this.container.get(eventSubscriberClass).dispatch(event);
    }
}
