package alouro.core.notifications.application.send_user_renamed;

import alouro.shared.domain.command.Command;
import alouro.shared.domain.message.Message;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

public final class SendUserRenamedNotificationCommand extends Command {
    private final String id;

    public SendUserRenamedNotificationCommand(final String id) {
        super();

        this.id = id;
    }

    public SendUserRenamedNotificationCommand(
            final String id,
            final String messageId,
            final String messageOccurredOn
    ) {
        super(messageId, messageOccurredOn);

        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendUserRenamedNotificationCommand that = (SendUserRenamedNotificationCommand) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String messageName() {
        return "notification.send_user_renamed";
    }

    @Override
    public String messageVersion() {
        return "1";
    }

    @Override
    public Message fromPrimitives(
            final String messageId,
            final String messageOccurredOn,
            final Map<String, Serializable> body
    ) {
        return new SendUserRenamedNotificationCommand(
                body.get("id").toString(),
                messageId,
                messageOccurredOn
        );
    }

    @Override
    public Map<String, Serializable> toPrimitives() {
        return Map.ofEntries(
                new AbstractMap.SimpleImmutableEntry<>("id", this.id)
        );
    }

    public String id() {
        return id;
    }
}