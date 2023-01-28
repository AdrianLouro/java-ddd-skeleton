package alouro.shared.domain.message;

public abstract class AggregateMessage extends Message {
    protected final String aggregateId;

    protected AggregateMessage(final String aggregateId) {
        super();

        this.aggregateId = aggregateId;
    }

    protected AggregateMessage(final String aggregateId, final String messageId, final String messageOccurredOn) {
        super(messageId, messageOccurredOn);

        this.aggregateId = aggregateId;
    }

    public final String aggregateId() {
        return this.aggregateId;
    }
}
