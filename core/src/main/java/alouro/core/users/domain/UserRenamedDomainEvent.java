package alouro.core.users.domain;

import alouro.shared.domain.event.DomainEvent;
import alouro.shared.domain.message.Message;

import java.io.Serializable;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;
import java.util.Objects;

public final class UserRenamedDomainEvent extends DomainEvent {
    private final String name;

    public UserRenamedDomainEvent(final String id, final String name) {
        super(id);

        this.name = name;
    }

    public UserRenamedDomainEvent(
            final String id,
            final String name,
            final String messageId,
            final String messageOccurredOn
    ) {
        super(id, messageId, messageOccurredOn);

        this.name = name;
    }

    public static UserRenamedDomainEvent from(final User user) {
        return new UserRenamedDomainEvent(user.id().value(), user.name().value());
    }

    @Override
    public String messageName() {
        return "user.renamed";
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
        return new UserRenamedDomainEvent(
                body.get("id").toString(),
                body.get("name").toString(),
                messageId,
                messageOccurredOn
        );
    }

    @Override
    public Map<String, Serializable> toPrimitives() {
        return Map.ofEntries(
                new SimpleImmutableEntry<>("id", this.aggregateId),
                new SimpleImmutableEntry<>("name", this.name)
        );
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRenamedDomainEvent that = (UserRenamedDomainEvent) o;
        return Objects.equals(aggregateId, that.aggregateId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateId, name);
    }

    public String name() {
        return name;
    }
}
