package alouro.core.users.domain;

import alouro.shared.Mock;
import alouro.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


public final class UserRepositoryMock extends Mock<UserRepository> {
    public void givenAUserList(final Criteria criteria, final List<User> users) {
        when(this.mock().matching(criteria)).thenReturn(users);
    }

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
