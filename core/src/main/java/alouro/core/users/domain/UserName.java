package alouro.core.users.domain;


import alouro.shared.domain.value_object.StringValueObject;

public final class UserName extends StringValueObject {
    public UserName(final String value) {
        super(value);

        this.ensureValidUserName();
    }

    private void ensureValidUserName() {
        if (this.value().length() < 2) {
            throw new UserNameTooShortException(this.value());
        }

        if (this.value().length() > 32) {
            throw new UserNameTooLongException(this.value());
        }
    }
}
