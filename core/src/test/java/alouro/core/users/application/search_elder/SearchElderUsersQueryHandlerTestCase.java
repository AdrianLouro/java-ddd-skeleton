package alouro.core.users.application.search_elder;

import alouro.core.users.UsersModuleUnitTestCase;
import alouro.core.users.application.UsersResponseObjectMother;
import alouro.core.users.domain.UserObjectMother;
import alouro.shared.domain.criteria.CriteriaBuilder;
import alouro.shared.domain.criteria.Filter;
import alouro.shared.domain.criteria.FilterOperator;
import alouro.shared.domain.criteria.Filters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

final class SearchElderUsersQueryHandlerTestCase extends UsersModuleUnitTestCase {
    private SearchElderUsersQueryHandler queryHandler;

    @BeforeEach
    void setUp() {
        this.queryHandler = new SearchElderUsersQueryHandler(
                new ElderUsersSearcher(
                        this.userRepository().mock(),
                        this.clock().mock()
                )
        );
    }

    @Test
    void should_find_elder_users() {
        final var currentDate = LocalDateTime.parse("2022-01-01T00:00:00");

        this.clock().givenACurrentDate(currentDate);

        final var criteria = CriteriaBuilder.empty().withFilters(
                new Filters(
                        List.of(
                                Filter.fromPrimitives("birthDate", FilterOperator.LT.value(), "1957-01-02")
                        )
                )
        ).build();

        final var elderUsers = List.of(UserObjectMother.elder(this.clock().mock()));

        this.userRepository().givenAUserList(criteria, elderUsers);

        final var query = SearchElderUsersQueryObjectMother.create(
                criteria.order().orderBy().value(),
                criteria.order().orderType().value(),
                criteria.limit().orElse(null),
                criteria.offset()
        );

        assertThat(
                this.queryHandler.handle(query),
                is(equalTo(UsersResponseObjectMother.from(elderUsers)))
        );
    }
}
