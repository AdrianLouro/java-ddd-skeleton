package alouro.users.application;

import alouro.users.domain.UserCannotBeUnderageException;
import alouro.users.UsersModuleUnitTestCase;
import alouro.users.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

final class UserCreatorTestCase extends UsersModuleUnitTestCase {

    private UserCreator creator;

    @BeforeEach
    void setUp() {
        this.creator = new UserCreator(this.userRepository(), this.domainEventPublisher());
    }

    @Test
    void should_not_create_users_with_invalid_name() {
        assertThrows(UserNameTooShortException.class, () -> new UserName("0"));

        assertThrows(UserNameTooLongException.class, () -> new UserName("012345678901234567890123456789012"));
    }

    @Test
    void should_not_create_underage_users() {
        this.shouldNowBe(LocalDateTime.parse("2022-01-01T00:00:00"));

        assertThrows(UserCannotBeUnderageException.class, () -> new UserBirthDate("2022-01-01", this.clock()));
    }

    @Test
    void should_create_users() {
        this.shouldNowBe(LocalDateTime.parse("2022-09-12T00:00:00"));

        final var user = UserObjectMother.create(
                UserIdObjectMother.random(),
                UserNameObjectMother.random(),
                new UserBirthDate("2000-01-01", this.clock())
        );

        final var event = UserCreatedDomainEventObjectMother.create(user.id(), user.name(), user.birthDate());

        this.creator.create(user.id(), user.name(), user.birthDate());

        this.shouldSave(user);
        this.shouldPublish(event);
    }
}