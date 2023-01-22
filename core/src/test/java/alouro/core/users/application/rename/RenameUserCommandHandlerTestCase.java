package alouro.core.users.application.rename;

import alouro.core.users.UsersModuleUnitTestCase;
import alouro.core.users.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static alouro.shared.ThrownMatcher.thrown;
import static org.hamcrest.MatcherAssert.assertThat;

final class RenameUserCommandHandlerTestCase extends UsersModuleUnitTestCase {

    private RenameUserCommandHandler commandHandler;

    @BeforeEach
    void setUp() {
        this.commandHandler = new RenameUserCommandHandler(

                new UserRenamer(
                        this.userRepository().mock(),
                        this.eventBus().mock()
                )
        );
    }

    @Test
    void should_raise_an_exception_if_the_user_does_not_exist() {
        final var command = RenameUserCommandObjectMother.random();

        this.userRepository().givenANonExistentUser(UserIdObjectMother.create(command.id()));

        final Runnable renameUser = () -> this.commandHandler.handle(command);

        assertThat(renameUser, thrown(UserNotFoundException.class));
    }

    @Test
    void should_not_publish_user_renamed_event_if_name_did_not_change() {
        this.clock().givenACurrentDate(LocalDateTime.parse("2022-01-01T00:00:00"));

        final var user = UserObjectMother.random(this.clock().mock());

        this.userRepository().givenAUser(user.id(), user);

        this.commandHandler.handle(RenameUserCommandObjectMother.from(user));

        this.eventBus().shouldHaveNotPublishedAnyDomainEvent();
    }

    @Test
    void should_rename_a_user() {
        this.clock().givenACurrentDate(LocalDateTime.parse("2022-01-01T00:00:00"));

        final var user = UserObjectMother.random(this.clock().mock());

        this.userRepository().givenAUser(user.id(), user);

        final var renamedUser = UserBuilder
                .fromUser(user, this.clock().mock())
                .withName("Another name")
                .build();

        this.commandHandler.handle(RenameUserCommandObjectMother.from(renamedUser));

        this.userRepository().shouldHaveSaved(renamedUser);
        this.eventBus().shouldHavePublished(UserRenamedDomainEventObjectMother.from(user));
    }
}