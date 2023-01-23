package alouro.core.users.domain;

import alouro.shared.domain.DomainError;

public final class UserNameTooShortException extends DomainError {
    public UserNameTooShortException(final String name) {
        super("user_name_too_long", String.format("User name <%s> is too short", name));
    }
}
