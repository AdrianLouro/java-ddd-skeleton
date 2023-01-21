package alouro.users.domain;

import alouro.Mock;

import java.util.Optional;

import static org.mockito.Mockito.*;


public final class UserRepositoryMock extends Mock<UserRepository> {
    public void givenAUser(final UserId id, final User user) {
        when(this.mock().search(id)).thenReturn(Optional.of(user));
    }

    public void givenANonExistentUser(final UserId id) {
        when(this.mock().search(id)).thenReturn(Optional.empty());
    }

    public void shouldHaveSaved(final User user) {
        verify(this.mock(), times(1)).save(user);
    }
}
