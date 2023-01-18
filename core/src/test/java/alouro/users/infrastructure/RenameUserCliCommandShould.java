package alouro.users.infrastructure;

import alouro.users.UsersModuleInfrastructureTestCase;
import alouro.users.domain.UserName;
import alouro.users.domain.UserObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class RenameUserCliCommandShould extends UsersModuleInfrastructureTestCase {

    private RenameUserCliCommand cliCommand;

    @BeforeEach
    void setUp() {
        this.cliCommand = new RenameUserCliCommand(this.userRenamer);
    }

    @Test
    void rename_a_user() {
        final var user = UserObjectMother.random(this.clock);
        final var newName = "new name";

        final var updatedUser = UserObjectMother.create(
                user.id().value(),
                new UserName(newName).value(),
                user.birthDate().value(),
                this.clock
        );

        this.persist(user);

        this.cliCommand.execute(
                Map.ofEntries(
                        new SimpleImmutableEntry<>("id", user.id().value()),
                        new SimpleImmutableEntry<>("name", newName)
                )
        );

        assertEquals(updatedUser, this.find(user));
    }
}
