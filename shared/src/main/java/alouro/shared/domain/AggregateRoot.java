package alouro.shared.domain;

import alouro.shared.domain.event.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {
    private final List<DomainEvent> recordedDomainEvents = new ArrayList<>();

     public final List<DomainEvent> pullDomainEvents() {
        final var pulledDomainEvents = new ArrayList<>(this.recordedDomainEvents);
        this.recordedDomainEvents.clear();

        return pulledDomainEvents;
    }

     protected final void push(DomainEvent event) {
        recordedDomainEvents.add(event);
    }
}
