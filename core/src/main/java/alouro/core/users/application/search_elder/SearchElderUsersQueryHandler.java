package alouro.core.users.application.search_elder;

import alouro.core.users.application.UsersResponse;
import alouro.shared.domain.criteria.Order;
import alouro.shared.domain.query.QueryHandler;

public final class SearchElderUsersQueryHandler implements QueryHandler<SearchElderUsersQuery, UsersResponse> {

    private final ElderUsersSearcher searcher;

    public SearchElderUsersQueryHandler(final ElderUsersSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public UsersResponse handle(final SearchElderUsersQuery query) {
        final var order = Order.fromPrimitives(
                query.orderBy().orElse(null),
                query.orderType().orElse(null)
        );

        return this.searcher.search(
                order,
                query.limit().orElse(null),
                query.offset().orElse(null)
        );
    }
}
