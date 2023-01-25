package alouro.shared.domain.criteria;

import java.util.Map;
import java.util.Objects;

public final class Filter {
    private final FilterField field;
    private final FilterOperator operator;
    private final FilterValue value;

    private Filter(final FilterField field, final FilterOperator operator, final FilterValue value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public static Filter fromValues(final Map<String, String> values) {
        return new Filter(
                new FilterField(values.get("field")),
                FilterOperator.from(values.get("operator")),
                new FilterValue(values.get("value"))
        );
    }

    public static Filter fromPrimitives(final String field, final String operator, final String value) {
        return new Filter(
                new FilterField(field),
                FilterOperator.from(operator),
                new FilterValue(value)
        );
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return field.equals(filter.field) && operator == filter.operator && value.equals(filter.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, operator, value);
    }

    public FilterField field() {
        return this.field;
    }

    public FilterOperator operator() {
        return this.operator;
    }

    public FilterValue value() {
        return this.value;
    }
}
