package alouro.users.infrastructure;

import alouro.users.UsersModuleInfrastructureTestCase;
import alouro.users.domain.UserIdObjectMother;
import alouro.users.domain.UserObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

final class InMemoryUserRepositoryTestCase extends UsersModuleInfrastructureTestCase {

    private InMemoryUserRepository repository;

    @BeforeEach
    void setUp() {
        this.repository = new InMemoryUserRepository();
    }

    @Test
    void should_not_find_a_non_existent_user() {
        assertThat(
                this.repository.search(UserIdObjectMother.random()).isEmpty(),
                is(true)
        );
    }

    @Test
    void should_save_a_new_user() {
        final var user = UserObjectMother.random(this.clock);

        this.repository.save(user);

        assertThat(
                this.repository.search(user.id()).orElseThrow(),
                is(equalTo(user))
        );
    }

    @Test
    void should_find_an_existing_user() {
        final var user = UserObjectMother.random(this.clock);

        this.repository.save(user);

        assertThat(
                this.repository.search(user.id()).orElseThrow(),
                is(equalTo(user))
        );
    }
}