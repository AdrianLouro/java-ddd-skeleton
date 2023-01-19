package alouro.users.infrastructure;

import alouro.users.UsersModuleInfrastructureTestCase;
import alouro.users.domain.UserIdObjectMother;
import alouro.users.domain.UserObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class InMemoryUserRepositoryShould extends UsersModuleInfrastructureTestCase {

    private InMemoryUserRepository repository;

    @BeforeEach
    void setUp() {
        this.repository = new InMemoryUserRepository();
    }

    @Test
    void not_find_a_non_existing_user() {
        assertTrue(this.repository.search(UserIdObjectMother.random()).isEmpty());
    }

    @Test
    void save_a_new_user() {
        final var user = UserObjectMother.random(this.clock);

        this.repository.save(user);

        assertTrue(this.repository.search(user.id()).isPresent());
    }

    @Test
    void find_an_existing_user() {
        final var user = UserObjectMother.random(this.clock);

        this.repository.save(user);

        assertEquals(user, this.repository.search(user.id()).orElseThrow());
    }
}