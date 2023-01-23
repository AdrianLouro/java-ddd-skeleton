package alouro.shared.domain.value_object;

import alouro.shared.domain.DomainError;

public final class InvalidUuidException extends DomainError {
    public InvalidUuidException(final String uuid) {
        super("invalid_uuid", String.format("The UUID <%s> is invalid", uuid));
    }
}
