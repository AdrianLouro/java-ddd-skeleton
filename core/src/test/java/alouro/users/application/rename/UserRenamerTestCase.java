package alouro.users.application.rename;

import alouro.users.UsersModuleUnitTestCase;
import alouro.users.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static alouro.ThrownMatcher.thrown;
import static org.hamcrest.MatcherAssert.assertThat;

final class UserRenamerTestCase extends UsersModuleUnitTestCase {

    private UserRenamer renamer;

    @BeforeEach
    void setUp() {
        this.renamer = new UserRenamer(
                this.userRepository().mock(),
                this.eventBus().mock()
        );
    }

    @Test
    void should_raise_an_exception_if_the_user_does_not_exist() {
        final var userId = UserIdObjectMother.random();

        this.userRepository().givenANonExistentUser(userId);

        final Runnable renameUser = () -> this.renamer.rename(userId.value(), UserNameObjectMother.random().value());

        assertThat(renameUser, thrown(UserNotFoundException.class));
    }

    @Test
    void should_not_publish_user_renamed_event_if_name_did_not_change() {
        this.clock().givenACurrentDate(LocalDateTime.parse("2022-01-01T00:00:00"));

        final var user = UserObjectMother.random(this.clock().mock());

        this.userRepository().givenAUser(user.id(), user);

        this.renamer.rename(user.id().value(), user.name().value());

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

        this.renamer.rename(user.id().value(), renamedUser.name().value());

        this.userRepository().shouldHaveSaved(renamedUser);
        this.eventBus().shouldHavePublished(UserRenamedDomainEventObjectMother.from(user));
    }
}