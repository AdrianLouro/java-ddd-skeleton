package alouro.users.infrastructure;

import alouro.users.UsersModuleInfrastructureTestCase;
import alouro.users.domain.UserIdObjectMother;
import alouro.users.domain.UserObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class InMemoryUserRepositoryTestCase extends UsersModuleInfrastructureTestCase {

    private InMemoryUserRepository repository;

    @BeforeEach
    void setUp() {
        this.repository = new InMemoryUserRepository();
    }

    @Test
    void should_not_find_an_existing_user() {
        assertNull(this.repository.find(UserIdObjectMother.random()));
    }

    @Test
    void should_save_new_user() {
        final var user = UserObjectMother.random(this.clock);

        this.repository.save(user);

        assertNotNull(this.repository.find(user.id()));
    }

    @Test
    void should_find_an_existing_user() {
        final var user = UserObjectMother.random(this.clock);

        this.repository.save(user);

        assertEquals(user, this.repository.find(user.id()));
    }
}