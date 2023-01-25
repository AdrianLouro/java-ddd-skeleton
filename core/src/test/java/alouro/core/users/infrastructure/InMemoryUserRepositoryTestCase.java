package alouro.core.users.infrastructure;

import alouro.core.users.UsersModuleInfrastructureTestCase;
import alouro.core.users.domain.User;
import alouro.core.users.domain.UserIdObjectMother;
import alouro.core.users.domain.UserObjectMother;
import alouro.shared.domain.criteria.CriteriaObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Comparator.comparing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

final class InMemoryUserRepositoryTestCase extends UsersModuleInfrastructureTestCase {

    private InMemoryUserRepository repository;

    @BeforeEach
    void setUp() {
        this.repository = new InMemoryUserRepository(this.clock());
    }

    @Test
    void should_save_a_new_user() {
        final var user = UserObjectMother.random(this.clock());

        this.repository.save(user);

        assertThat(
                this.repository.search(user.id()).orElseThrow(),
                is(equalTo(user))
        );
    }

    @Test
    void should_not_find_a_non_existent_user() {
        assertThat(
                this.repository.search(UserIdObjectMother.random()).isEmpty(),
                is(true)
        );
    }

    @Test
    void should_find_an_existing_user() {
        final var user = UserObjectMother.random(this.clock());

        this.repository.save(user);

        assertThat(
                this.repository.search(user.id()).orElseThrow(),
                is(equalTo(user))
        );
    }

    @Test
    void should_search_all_users() {
        final var users = List.of(UserObjectMother.random(this.clock()), UserObjectMother.random(this.clock()));

        this.save(users);

        final var expectedUsers = users
                .stream()
                .sorted(comparing(user -> user.id().value())) // We need to sort the list to match the repository default order
                .toList();

        assertThat(
                this.repository.matching(CriteriaObjectMother.empty()),
                is(equalTo(expectedUsers))
        );
    }

    private void save(final List<User> users) {
        users.forEach(user -> this.repository.save(user));
    }
}