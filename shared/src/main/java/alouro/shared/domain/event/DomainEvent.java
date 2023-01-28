package alouro.shared.domain.event;

import alouro.shared.domain.message.AggregateMessage;

public abstract class DomainEvent extends AggregateMessage {

    protected DomainEvent(final String aggregateId) {
        super(aggregateId);
    }

    protected DomainEvent(final String aggregateId, final String messageId, final String messageOccurredOn) {
        super(aggregateId, messageId, messageOccurredOn);
    }

    @Override
    public final String messageType() {
        return "domain_event";
    }
}
