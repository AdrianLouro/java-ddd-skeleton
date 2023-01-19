package alouro.users.domain;

public final class UserFinder {

    private final UserRepository userRepository;

    public UserFinder(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User find(final UserId id) {
        return this.userRepository
                .search(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
