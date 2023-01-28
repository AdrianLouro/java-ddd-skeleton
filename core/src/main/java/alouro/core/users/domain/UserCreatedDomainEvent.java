package alouro.core.users.domain;

import alouro.shared.domain.event.DomainEvent;
import alouro.shared.domain.message.Message;

import java.io.Serializable;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;
import java.util.Objects;

public final class UserCreatedDomainEvent extends DomainEvent {
    private final String name;
    private final String birthDate;

    public UserCreatedDomainEvent(final String id, final String name, final String birthDate) {
        super(id);

        this.name = name;
        this.birthDate = birthDate;
    }

    public UserCreatedDomainEvent(
            final String id,
            final String name,
            final String birthDate,
            final String messageId,
            final String messageOccurredOn
    ) {
        super(id, messageId, messageOccurredOn);

        this.name = name;
        this.birthDate = birthDate;
    }

    public static UserCreatedDomainEvent from(final User user) {
        return new UserCreatedDomainEvent(user.id().value(), user.name().value(), user.birthDate().value());
    }

    @Override
    public String messageName() {
        return "user.created";
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
        return new UserCreatedDomainEvent(
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
                new SimpleImmutableEntry<>("id", this.aggregateId),
                new SimpleImmutableEntry<>("name", this.name),
                new SimpleImmutableEntry<>("birthDate", this.birthDate)
        );
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreatedDomainEvent that = (UserCreatedDomainEvent) o;
        return Objects.equals(aggregateId, that.aggregateId) && Objects.equals(name, that.name) && Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateId, name, birthDate);
    }

    public String name() {
        return name;
    }

    public String birthDate() {
        return birthDate;
    }
}
