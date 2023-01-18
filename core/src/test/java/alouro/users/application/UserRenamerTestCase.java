package alouro.users.application;

import alouro.users.UsersModuleUnitTestCase;
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
    void should_not_update_non_existing_users() {
        this.shouldNotFindUser();

        assertThrows(
                UserNotFoundException.class,
                () -> this.renamer.rename(UserIdObjectMother.random(), UserNameObjectMother.random())
        );
    }

    @Test
    void should_not_publish_user_renamed_event_if_name_id_not_change() {
        this.shouldNowBe(LocalDateTime.parse("2022-01-01T00:00:00"));

        final var user = UserObjectMother.random(this.clock());

        this.shouldFind(user.id(), user);

        this.renamer.rename(user.id(), user.name());

        this.shouldNotPublishAnyDomainEvent();
    }

    @Test
    void should_rename_users() {
        this.shouldNowBe(LocalDateTime.parse("2022-01-01T00:00:00"));

        final var user = UserObjectMother.random(this.clock());

        final var renamedUser = UserObjectMother.create(
                user.id().value(),
                UserNameObjectMother.create("Another name").value(),
                user.birthDate().value(),
                this.clock()
        );

        this.shouldFind(user.id(), user);

        this.renamer.rename(user.id(), renamedUser.name());

        this.shouldSave(renamedUser);
        this.shouldPublish(UserRenamedDomainEventObjectMother.create(user.id(), renamedUser.name()));
    }
}