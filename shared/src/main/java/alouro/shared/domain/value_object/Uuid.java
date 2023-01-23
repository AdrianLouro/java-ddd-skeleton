package alouro.shared.domain.value_object;

import java.util.regex.Pattern;

public class Uuid extends StringValueObject {

    public Uuid(final String value) {
        super(value);

        this.ensureIsValidUuid();
    }

    private void ensureIsValidUuid() {
        if (this.value() == null) {
            throw new InvalidUuidException(this.value());
        }

        final var uuidRegex = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

        if (!uuidRegex.matcher(this.value()).matches()) {
            throw new InvalidUuidException(this.value());
        }
    }
}
