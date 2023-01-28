package alouro.core.users.application.rename;

import alouro.shared.domain.command.Command;
import alouro.shared.domain.message.Message;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;

public final class RenameUserCommand extends Command {
    private final String id;
    private final String name;

    public RenameUserCommand(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public RenameUserCommand(final String id, final String name, final String messageId, final String messageOccurredOn) {
        super(messageId, messageOccurredOn);

        this.id = id;
        this.name = name;
    }

    @Override
    public String messageName() {
        return "user.rename";
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
        return new RenameUserCommand(
                body.get("id").toString(),
                body.get("name").toString(),
                messageId,
                messageOccurredOn
        );
    }

    @Override
    public Map<String, Serializable> toPrimitives() {
        return Map.ofEntries(
                new AbstractMap.SimpleImmutableEntry<>("id", this.id),
                new AbstractMap.SimpleImmutableEntry<>("name", this.name)
        );
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }
}
