package alouro.core.users.application.search_elder;

import alouro.core.users.application.UserResponse;
import alouro.core.users.application.UsersResponse;
import alouro.core.users.domain.UserRepository;
import alouro.shared.domain.Clock;
import alouro.shared.domain.criteria.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static alouro.core.users.domain.User.MIN_ELDER_AGE;

public final class ElderUsersSearcher {
    private final UserRepository repository;
    private final Clock clock;

    public ElderUsersSearcher(final UserRepository repository, final Clock clock) {
        this.repository = repository;
        this.clock = clock;
    }

    public UsersResponse search(final Order order, final Integer limit, final Integer offset) {
        final var criteria = new Criteria(this.getFilters(), order, limit, offset);

        return new UsersResponse(
                repository
                        .matching(criteria)
                        .stream()
                        .map(UserResponse::fromAggregate)
                        .toList()
        );
    }

    private Filters getFilters() {
        final var limitDate = this.clock
                .now()
                .minusYears(MIN_ELDER_AGE)
                .plusDays(1)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return new Filters(
                List.of(
                        Filter.fromPrimitives("birthDate", FilterOperator.LT.value(), limitDate)
                )
        );
    }
}
