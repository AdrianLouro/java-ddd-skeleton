package alouro.core.users.domain;

import alouro.shared.domain.DomainError;

public final class UserNameTooLongException extends DomainError {
    public UserNameTooLongException(final String name) {
        super("user_name_too_long", String.format("User name <%s> is too long", name));
    }
}
