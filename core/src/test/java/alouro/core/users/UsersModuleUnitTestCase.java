package alouro.core.users;

import alouro.shared.UnitTestCase;
import alouro.core.users.domain.UserRepositoryMock;

public abstract class UsersModuleUnitTestCase extends UnitTestCase {
    private UserRepositoryMock userRepository;

    protected final UserRepositoryMock userRepository() {
        if (this.userRepository == null) {
            this.userRepository = new UserRepositoryMock();
        }

        return this.userRepository;
    }
}
