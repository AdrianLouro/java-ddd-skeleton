package alouro.users.infrastructure;

import alouro.users.UsersModuleInfrastructureTestCase;
import alouro.users.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

final class CreateUserCliCommandTestCase extends UsersModuleInfrastructureTestCase {

    private CreateUserCliCommand cliCommand;

    @BeforeEach
    void setUp() {
        this.cliCommand = new CreateUserCliCommand(this.userCreator);
    }

    @Test
    void should_create_user() {
        final var user = UserObjectMother.random(this.clock);

        this.cliCommand.execute(
                Map.ofEntries(
                        new SimpleImmutableEntry<>("id", user.id().value()),
                        new SimpleImmutableEntry<>("name", user.name().value()),
                        new SimpleImmutableEntry<>("birthDate", user.birthDate().value())
                )
        );

        assertEquals(user, this.find(user));
    }
}
