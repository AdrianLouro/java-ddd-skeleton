package alouro.users.application;

import alouro.users.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

final class UserRenamerTestCase extends UsersModuleUnitTestCase {

    private UserRenamer renamer;

    @BeforeEach
    void setUp() {
        this.renamer = new UserRenamer(this.userRepository(), this.domainEventPublisher());
    }

    @Test
    void should_raise_an_exception_if_the_user_does_not_exist() {
        final var userId = UserIdObjectMother.random();

        this.givenANonExistentUser(userId);

        assertThrows(
                UserNotFoundException.class,
                () -> this.renamer.rename(userId.value(), UserNameObjectMother.random().value())
        );
    }

    @Test
    void should_not_publish_user_renamed_event_if_name_did_not_change() {
        this.givenACurrentDate(LocalDateTime.parse("2022-01-01T00:00:00"));

        final var user = UserObjectMother.random(this.clock());

        this.givenAUser(user.id(), user);

        this.renamer.rename(user.id().value(), user.name().value());

        this.shouldNotPublishAnyDomainEvent();
    }

    @Test
    void should_rename_a_user() {
        this.givenACurrentDate(LocalDateTime.parse("2022-01-01T00:00:00"));

        final var user = UserObjectMother.random(this.clock());

        this.givenAUser(user.id(), user);

        final var renamedUser = UserBuilder
                .fromUser(user, this.clock())
                .withName("Another name")
                .build();

        this.renamer.rename(user.id().value(), renamedUser.name().value());

        this.shouldSave(renamedUser);
        this.shouldPublish(UserRenamedDomainEventObjectMother.from(user));
    }
}