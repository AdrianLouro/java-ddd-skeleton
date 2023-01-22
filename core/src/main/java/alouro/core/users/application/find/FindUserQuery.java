package alouro.core.users.application.find;

import alouro.shared.domain.query.Query;

public final class FindUserQuery extends Query {
    private final String id;

    public FindUserQuery(final String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}