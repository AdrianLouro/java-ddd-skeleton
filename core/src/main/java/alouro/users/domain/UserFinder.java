package alouro.users.domain;

public final class UserFinder {

    private final UserRepository userRepository;

    public UserFinder(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User find(final UserId id) {
        final User user = this.userRepository.find(id);

        ensureUserExists(user);

        return user;
    }

    private static void ensureUserExists(final User user) {
        if (user == null) {
            throw new UserNotFoundException();
        }
    }
}
