package alouro.core.users.infrastructure.cli_command;

import alouro.core.users.UsersModuleInfrastructureTestCase;
import alouro.core.users.application.UsersResponseObjectMother;
import alouro.core.users.domain.User;
import alouro.core.users.domain.UserObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static alouro.shared.domain.criteria.OrderType.ASC;
import static java.util.Comparator.comparing;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

final class SearchElderUsersCliCommandTestCase extends UsersModuleInfrastructureTestCase {

    private SearchElderUsersCliCommand cliCommand;

    @BeforeEach
    void setUp() {
        this.cliCommand = new SearchElderUsersCliCommand(this.queryBus());
    }

    @Test
    void should_find_elder_users() {
        final var elderUser1 = UserObjectMother.elder(this.clock());
        final var elderUser2 = UserObjectMother.elder(this.clock());
        final var notElderUser1 = UserObjectMother.notElder(this.clock());
        final var notElderUser2 = UserObjectMother.notElder(this.clock());

        final var users = List.of(elderUser1, notElderUser1, notElderUser2, elderUser2);

        this.save(users.toArray(User[]::new));

        final var usersResponse = this.cliCommand.execute(
                Map.ofEntries(
                        new SimpleImmutableEntry<>("orderBy", "id"),
                        new SimpleImmutableEntry<>("orderType", ASC.value()),
                        new SimpleImmutableEntry<>("limit", "50"),
                        new SimpleImmutableEntry<>("offset", "0")
                )
        );

        final var expectedUsersResponse = UsersResponseObjectMother.from(
                Stream.of(elderUser1, elderUser2)
                        .sorted(comparing(user -> user.id().value())) // We need to sort the list to match the repository default order
                        .toList()
        );

        assertThat(
                usersResponse,
                is(equalTo(expectedUsersResponse))
        );
    }
}