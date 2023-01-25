package alouro.core.users.application.search_elder;

import alouro.shared.domain.query.Query;

import java.util.Optional;

public final class SearchElderUsersQuery extends Query {
    private final String orderBy;
    private final String orderType;
    private final Integer limit;
    private final Integer offset;

    public SearchElderUsersQuery(final String orderBy, final String orderType, final Integer limit, final Integer offset) {
        this.orderBy = orderBy;
        this.orderType = orderType;
        this.limit = limit;
        this.offset = offset;
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
