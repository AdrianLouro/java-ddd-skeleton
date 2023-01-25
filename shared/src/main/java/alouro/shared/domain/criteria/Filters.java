package alouro.shared.domain.criteria;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Collections.emptyList;

public final class Filters {
    private final List<Filter> filters;

    public Filters(final List<Filter> filters) {
        this.filters = filters;
    }

    public static Filters from(final List<Map<String, String>> filters) {
        return new Filters(filters.stream().map(Filter::fromValues).toList());
    }

    public static Filters none() {
        return new Filters(emptyList());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filters filters1 = (Filters) o;
        return filters.equals(filters1.filters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filters);
    }

    public List<Filter> filters() {
        return this.filters;
    }
}
