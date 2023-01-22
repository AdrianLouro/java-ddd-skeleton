package alouro.shared.domain;

import alouro.shared.Mock;
import alouro.shared.domain.event.DomainEvent;
import alouro.shared.domain.event.EventBus;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public final class EventBusMock extends Mock<EventBus> {

    public void shouldHavePublished(final DomainEvent domainEvent) {
        verify(this.mock(), times(1)).publish(List.of(domainEvent));
    }

    public void shouldHaveNotPublishedAnyDomainEvent() {
        verify(this.mock(), times(1)).publish(List.of());
    }
}
