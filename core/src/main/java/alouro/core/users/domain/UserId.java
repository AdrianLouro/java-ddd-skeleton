package alouro.core.users.domain;


import alouro.shared.domain.value_object.Uuid;

public final class UserId extends Uuid {
    public UserId(final String value) {
        super(value);
    }
}
