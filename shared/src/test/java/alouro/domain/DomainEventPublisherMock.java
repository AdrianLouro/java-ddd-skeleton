package alouro.domain;

import alouro.Mock;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public final class DomainEventPublisherMock extends Mock<DomainEventPublisher> {

    public void shouldHavePublished(final DomainEvent domainEvent) {
        verify(this.mock(), times(1)).publish(List.of(domainEvent));
    }

    public void shouldHaveNotPublishedAnyDomainEvent() {
        verify(this.mock(), times(1)).publish(List.of());
    }
}
