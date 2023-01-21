package alouro.users.infrastructure;

import alouro.users.UsersModuleInfrastructureTestCase;
import alouro.users.application.UserResponse;
import alouro.users.domain.UserIdObjectMother;
import alouro.users.domain.UserNotFoundException;
import alouro.users.domain.UserObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;

import static alouro.ThrownMatcher.thrown;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

final class FindUserCliCommandTestCase extends UsersModuleInfrastructureTestCase {

    private FindUserCliCommand cliCommand;

    @BeforeEach
    void setUp() {
        this.cliCommand = new FindUserCliCommand(this.queryBus());
    }

    @Test
    void should_raise_an_exception_if_the_user_does_not_exist() {
        final Runnable executeCommand = () -> this.cliCommand.execute(
                Map.ofEntries(
                        new SimpleImmutableEntry<>("id", UserIdObjectMother.random().value())
                )
        );

        assertThat(executeCommand, thrown(UserNotFoundException.class));
    }

    @Test
    void should_find_a_user() {
        final var user = UserObjectMother.random(this.clock());

        this.save(user);

        final var userResponse = this.cliCommand.execute(
                Map.ofEntries(
                        new SimpleImmutableEntry<>("id", user.id().value())
                )
        );

        assertThat(
                userResponse,
                is(equalTo(UserResponse.from(user)))
        );
    }
}