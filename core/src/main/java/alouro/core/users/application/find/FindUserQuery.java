package alouro.core.users.application.find;

import alouro.shared.domain.message.Message;
import alouro.shared.domain.query.Query;

import java.io.Serializable;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;

public final class FindUserQuery extends Query {
    private final String id;

    public FindUserQuery(final String id) {
        super();

        this.id = id;
    }

    public FindUserQuery(final String id, final String messageId, final String messageOccurredOn) {
        super(messageId, messageOccurredOn);

        this.id = id;
    }

    @Override
    public String messageName() {
        return "user.find";
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
        return new FindUserQuery(
                body.get("id").toString(),
                messageId,
                messageOccurredOn
        );
    }

    @Override
    public Map<String, Serializable> toPrimitives() {
        return Map.ofEntries(
                new SimpleImmutableEntry<>("id", this.id)
        );
    }

    public String id() {
        return id;
    }
}
