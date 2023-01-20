package alouro.users.application;

import alouro.domain.value_object.InvalidDateException;
import alouro.domain.value_object.InvalidUuidException;
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
        this.creator = new UserCreator(
                this.userRepository().mock(),
                this.domainEventPublisher().mock(),
                this.clock().mock()
        );
    }

    @Test
    void should_raise_an_exception_if_the_id_is_bad_formatted() {
        assertThrows(
                InvalidUuidException.class,
                () -> new UserId("123456789")
        );
    }

    @Test
    void should_raise_an_exception_if_the_name_is_too_short() {
        assertThrows(
                UserNameTooShortException.class,
                () -> new UserName("0")
        );
    }

    @Test
    void should_raise_an_exception_if_the_name_is_too_long() {
        assertThrows(
                UserNameTooLongException.class,
                () -> new UserName("012345678901234567890123456789012")
        );
    }

    @Test
    void should_raise_an_exception_if_the_birth_date_is_bad_formatted() {
        this.clock().givenACurrentDate(LocalDateTime.parse("2022-01-01T00:00:00"));

        assertThrows(
                InvalidDateException.class,
                () -> new UserBirthDate("01-01-2000", this.clock().mock())
        );
    }


    @Test
    void should_raise_an_exception_if_the_user_is_underage() {
        this.clock().givenACurrentDate(LocalDateTime.parse("2022-01-01T00:00:00"));

        assertThrows(
                UserCannotBeUnderageException.class,
                () -> new UserBirthDate("2022-01-01", this.clock().mock())
        );
    }

    @Test
    void should_create_a_user() {
        this.clock().givenACurrentDate(LocalDateTime.parse("2022-09-12T00:00:00"));

        final var user = UserObjectMother.random(this.clock().mock());
        final var event = UserCreatedDomainEventObjectMother.from(user);

        this.creator.create(user.id().value(), user.name().value(), user.birthDate().value());

        this.userRepository().shouldHaveSaved(user);
        this.domainEventPublisher().shouldHavePublished(event);
    }
}