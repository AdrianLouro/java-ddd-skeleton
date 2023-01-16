package alouro.users.domain;


import alouro.domain.value_object.Uuid;

public final class UserId extends Uuid {
    public UserId(final String value) {
        super(value);
    }
}
