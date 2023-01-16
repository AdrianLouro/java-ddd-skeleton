package alouro.users.domain;


import alouro.domain.value_object.StringValueObject;

public final class UserName extends StringValueObject {
    public UserName(final String value) {
        super(value);

        this.ensureValidUserName();
    }

    private void ensureValidUserName() {
        if (this.value().length() < 2) {
            throw new UserNameTooShortException();
        }

        if (this.value().length() > 32) {
            throw new UserNameTooLongException();
        }
    }
}
