package alouro.users.domain;

import alouro.Mock;

import java.util.Optional;

import static org.mockito.Mockito.*;


public class UserRepositoryMock extends Mock<UserRepository> {
    public final void givenAUser(final UserId id, final User user) {
        when(this.mock().search(id)).thenReturn(Optional.of(user));
    }

    public final void givenANonExistentUser(final UserId id) {
        when(this.mock().search(id)).thenReturn(Optional.empty());
    }

    public final void shouldHaveSaved(final User user) {
        verify(this.mock(), times(1)).save(user);
    }
}
