package alouro.users.infrastructure;

import alouro.users.UsersModuleInfrastructureTestCase;
import alouro.users.domain.UserBuilder;
import alouro.users.domain.UserNotFoundException;
import alouro.users.domain.UserObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class RenameUserCliCommandTestCase extends UsersModuleInfrastructureTestCase {

    private RenameUserCliCommand cliCommand;

    @BeforeEach
    void setUp() {
        this.cliCommand = new RenameUserCliCommand(this.userRenamer);
    }

    @Test
    void should_raise_an_exception_if_the_user_does_not_exist() {
        final var user = UserObjectMother.random(this.clock);

        final Executable executeCommand = () -> this.cliCommand.execute(
                Map.ofEntries(
                        new SimpleImmutableEntry<>("id", user.id().value()),
                        new SimpleImmutableEntry<>("name", user.name().value())
                )
        );

        assertThrows(UserNotFoundException.class, executeCommand);
    }

    @Test
    void should_rename_a_user() {
        final var user = UserObjectMother.random(this.clock);
        final var newName = "new name";

        final var updatedUser = UserBuilder
                .fromUser(user, this.clock)
                .withName(newName)
                .build();

        this.save(user);

        this.cliCommand.execute(
                Map.ofEntries(
                        new SimpleImmutableEntry<>("id", user.id().value()),
                        new SimpleImmutableEntry<>("name", newName)
                )
        );

        assertThat(
                this.search(user).orElseThrow(),
                is(equalTo(updatedUser))
        );
    }
}
