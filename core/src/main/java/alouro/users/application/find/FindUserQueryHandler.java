package alouro.users.application.find;

import alouro.domain.query.QueryHandler;
import alouro.users.application.UserResponse;
import alouro.users.domain.UserId;

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
