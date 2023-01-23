package alouro.shared.domain.value_object;

import alouro.shared.domain.DomainError;

public final class InvalidDateException extends DomainError {

    public InvalidDateException(final String date) {
        super("invalid_date", String.format("The date <%s> is invalid", date));
    }
}
