package alouro.core.users.application.create;

import alouro.shared.domain.command.Command;
import alouro.shared.domain.message.Message;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;

public final class CreateUserCommand extends Command {
    private final String id;
    private final String name;
    private final String birthDate;

    public CreateUserCommand(final String id, final String name, final String birthDate) {
        super();

        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public CreateUserCommand(
            final String id,
            final String name,
            final String birthDate,
            final String messageId,
            final String messageOccurredOn
    ) {
        super(messageId, messageOccurredOn);

        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String messageName() {
        return "user.create";
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
        return new CreateUserCommand(
                body.get("id").toString(),
                body.get("name").toString(),
                body.get("birthDate").toString(),
                messageId,
                messageOccurredOn
        );
    }

    @Override
    public Map<String, Serializable> toPrimitives() {
        return Map.ofEntries(
                new AbstractMap.SimpleImmutableEntry<>("id", this.id),
                new AbstractMap.SimpleImmutableEntry<>("name", this.name),
                new AbstractMap.SimpleImmutableEntry<>("birthDate", this.name)
        );
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String birthDate() {
        return birthDate;
    }
}