package alouro.shared.domain.message;

import alouro.shared.domain.Utils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public abstract class Message {
    private final String messageId;
    private final String messageOccurredOn;

    protected Message() {
        this.messageId = UUID.randomUUID().toString();
        this.messageOccurredOn = Utils.dateToString(LocalDateTime.now());
    }

    protected Message(final String messageId, final String messageOccurredOn) {
        this.messageId = messageId;
        this.messageOccurredOn = messageOccurredOn;
    }

    public abstract String messageName();

    public abstract String messageVersion();

    public abstract String messageType();

    public abstract Message fromPrimitives(
            final String messageId,
            final String messageOccurredOn,
            final Map<String, Serializable> body
    );

    public abstract Map<String, Serializable> toPrimitives();

    public final String messageId() {
        return this.messageId;
    }

    public final String messageOccurredOn() {
        return this.messageOccurredOn;
    }
}
