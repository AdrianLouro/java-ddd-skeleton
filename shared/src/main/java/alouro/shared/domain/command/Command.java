package alouro.shared.domain.command;

import alouro.shared.domain.message.Message;

public abstract class Command extends Message {
    protected Command() {
        super();
    }

    protected Command(final String messageId, final String messageOccurredOn) {
        super(messageId, messageOccurredOn);
    }

    @Override
    public final String messageType() {
        return "command";
    }
}
