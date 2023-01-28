package alouro.shared.domain.query;

import alouro.shared.domain.message.Message;

public abstract class Query extends Message {
    protected Query() {
        super();
    }

    protected Query(final String messageId, final String messageOccurredOn) {
        super(messageId, messageOccurredOn);
    }

    @Override
    public final String messageType() {
        return "query";
    }
}