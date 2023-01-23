package alouro.core.users.domain;

import alouro.shared.domain.DomainError;

public final class UserNotFoundException extends DomainError {
    public UserNotFoundException(final UserId id) {
        super("user_not_found", String.format("User <%s> was not found", id.value()));
    }
}
