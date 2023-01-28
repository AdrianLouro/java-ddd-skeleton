package alouro.core.users.application.search_elder;

import alouro.shared.domain.message.Message;
import alouro.shared.domain.query.Query;

import java.io.Serializable;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;
import java.util.Optional;

public final class SearchElderUsersQuery extends Query {
    private final String orderBy;
    private final String orderType;
    private final Integer limit;
    private final Integer offset;

    public SearchElderUsersQuery(
            final String orderBy,
            final String orderType,
            final Integer limit,
            final Integer offset
    ) {
        this.orderBy = orderBy;
        this.orderType = orderType;
        this.limit = limit;
        this.offset = offset;
    }

    public SearchElderUsersQuery(
            final String orderBy,
            final String orderType,
            final Integer limit,
            final Integer offset,
            final String messageId,
            final String messageOccurredOn
    ) {
        super(messageId, messageOccurredOn);

        this.orderBy = orderBy;
        this.orderType = orderType;
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public String messageName() {
        return "user.search_elder";
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
        return new SearchElderUsersQuery(
                body.get("orderBy").toString(),
                body.get("orderType").toString(),
                Integer.valueOf(body.get("limit").toString()),
                Integer.valueOf(body.get("offset").toString()),
                messageId,
                messageOccurredOn
        );
    }

    @Override
    public Map<String, Serializable> toPrimitives() {
        return Map.ofEntries(
                new SimpleImmutableEntry<>("orderBy", this.orderBy),
                new SimpleImmutableEntry<>("orderType", this.orderType),
                new SimpleImmutableEntry<>("limit", this.limit),
                new SimpleImmutableEntry<>("offset", this.offset)
        );
    }

    public Optional<String> orderBy() {
        return Optional.ofNullable(this.orderBy);
    }

    public Optional<String> orderType() {
        return Optional.ofNullable(this.orderType);
    }

    public Optional<Integer> limit() {
        return Optional.ofNullable(this.limit);
    }

    public Optional<Integer> offset() {
        return Optional.ofNullable(this.offset);
    }
}
