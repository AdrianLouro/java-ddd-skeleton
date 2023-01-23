package alouro.core.users.domain;

import alouro.shared.domain.DomainError;

public final class UserCannotBeUnderageException extends DomainError {
    public UserCannotBeUnderageException(final String birthDate) {
        super("user_cannot_be_underage", String.format("User cannot be underage: %s", birthDate));
    }
}
