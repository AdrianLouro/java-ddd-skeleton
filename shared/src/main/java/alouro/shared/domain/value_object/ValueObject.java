package alouro.shared.domain.value_object;

import java.util.Objects;

public abstract class ValueObject<T> {

    private final T value;

    protected ValueObject(final T value) {
        this.value = value;
    }

    public final T value() {
        return value;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var that = (ValueObject<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(value);
    }
}
