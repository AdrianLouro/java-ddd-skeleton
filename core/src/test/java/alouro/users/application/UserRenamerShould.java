package alouro.users.application;

import alouro.users.UsersModuleUnitTestCase;
import alouro.users.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

final class UserRenamerShould extends UsersModuleUnitTestCase {

    private UserRenamer renamer;

    @BeforeEach
    void setUp() {
        this.renamer = new UserRenamer(this.userRepository(), this.domainEventPublisher());
    }

    @Test
    void not_update_a_non_existing_user() {
        final var userId = UserIdObjectMother.random();

        this.shouldNotFindUser(userId);

        assertThrows(
                UserNotFoundException.class,
                () -> this.renamer.rename(userId.value(), UserNameObjectMother.random().value())
        );
    }

    @Test
    void not_publish_user_renamed_event_if_name_did_not_change() {
        this.shouldNowBe(LocalDateTime.parse("2022-01-01T00:00:00"));

        final var user = UserObjectMother.random(this.clock());

        this.shouldFindUser(user.id(), user);

        this.renamer.rename(user.id().value(), user.name().value());

        this.shouldNotPublishAnyDomainEvent();
    }

    @Test
    void rename_a_user() {
        this.shouldNowBe(LocalDateTime.parse("2022-01-01T00:00:00"));

        final var user = UserObjectMother.random(this.clock());

        final var renamedUser = UserBuilder
                .fromUser(user, this.clock())
                .withName("Another name")
                .build();

        this.shouldFindUser(user.id(), user);

        this.renamer.rename(user.id().value(), renamedUser.name().value());

        this.shouldSave(renamedUser);
        this.shouldPublish(UserRenamedDomainEventObjectMother.from(user));
    }
}