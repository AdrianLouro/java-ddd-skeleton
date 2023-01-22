package alouro.core.users.infrastructure;

import alouro.core.users.UsersModuleInfrastructureTestCase;
import alouro.core.users.domain.UserObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

final class CreateUserCliCommandTestCase extends UsersModuleInfrastructureTestCase {

    private CreateUserCliCommand cliCommand;

    @BeforeEach
    void setUp() {
        this.cliCommand = new CreateUserCliCommand(this.commandBus());
    }

    @Test
    void should_create_a_user() {
        final var user = UserObjectMother.random(this.clock());

        this.cliCommand.execute(
                Map.ofEntries(
                        new SimpleImmutableEntry<>("id", user.id().value()),
                        new SimpleImmutableEntry<>("name", user.name().value()),
                        new SimpleImmutableEntry<>("birthDate", user.birthDate().value())
                )
        );

        assertThat(
                user,
                is(equalTo(this.search(user).orElseThrow()))
        );
    }
}
