package alouro.shared.domain.value_object;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Date extends StringValueObject {

    public Date(final String value) {
        super(value);

        this.ensureIsValidDate();
    }

    protected final LocalDate localDate() {
        return LocalDate.parse(this.value());
    }

    private void ensureIsValidDate() {
        try {
            LocalDate.parse(this.value());
        } catch (DateTimeParseException exception) {
            throw new InvalidDateException(this.value());
        }
    }
}
