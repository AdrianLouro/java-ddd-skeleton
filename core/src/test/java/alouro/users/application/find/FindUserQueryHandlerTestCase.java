package alouro.users.application.find;

import alouro.users.UsersModuleUnitTestCase;
import alouro.users.application.UserResponseObjectMother;
import alouro.users.domain.UserIdObjectMother;
import alouro.users.domain.UserNotFoundException;
import alouro.users.domain.UserObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static alouro.ThrownMatcher.thrown;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

final class FindUserQueryHandlerTestCase extends UsersModuleUnitTestCase {

    private FindUserQueryHandler queryHandler;

    @BeforeEach
    void setUp() {
        this.queryHandler = new FindUserQueryHandler(
                new UserFinder(
                        new alouro.users.domain.UserFinder(
                                this.userRepository().mock()
                        )
                )
        );
    }

    @Test
    void should_raise_an_exception_if_the_user_does_not_exist() {
        final var query = FindUserQueryObjectMother.random();

        this.userRepository().givenANonExistentUser(UserIdObjectMother.create(query.id()));

        final Runnable findUser = () -> this.queryHandler.handle(query);

        assertThat(findUser, thrown(UserNotFoundException.class));
    }

    @Test
    void should_find_a_user() {
        this.clock().givenACurrentDate(LocalDateTime.parse("2022-01-01T00:00:00"));

        final var user = UserObjectMother.random(this.clock().mock());

        this.userRepository().givenAUser(user.id(), user);

        assertThat(
                this.queryHandler.handle(FindUserQueryObjectMother.from(user)),
                is(equalTo(UserResponseObjectMother.from(user)))
        );
    }
}