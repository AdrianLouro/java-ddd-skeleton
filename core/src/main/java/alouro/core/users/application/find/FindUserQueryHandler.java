package alouro.core.users.application.find;

import alouro.core.users.application.UserResponse;
import alouro.core.users.domain.UserId;
import alouro.shared.domain.query.QueryHandler;

public final class FindUserQueryHandler implements QueryHandler<FindUserQuery, UserResponse> {

    private final UserFinder finder;

    public FindUserQueryHandler(final UserFinder finder) {
        this.finder = finder;
    }

    @Override
    public UserResponse handle(final FindUserQuery query) {
        return this.finder.find(new UserId(query.id())); // TODO: should the ValueObject be instantiated here or in an inner layer ???
    }
}
