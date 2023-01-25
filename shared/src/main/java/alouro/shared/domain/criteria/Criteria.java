package alouro.shared.domain.criteria;

import java.util.Objects;
import java.util.Optional;

public final class Criteria {
    private final Filters filters;
    private final Order order;
    private final Integer limit;
    private final Integer offset;

    public Criteria(final Filters filters, final Order order, final Integer limit, final Integer offset) {
        this.filters = filters;
        this.order = order;
        this.limit = limit;
        this.offset = Optional.ofNullable(offset).orElse(0);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Criteria criteria = (Criteria) o;
        return filters.equals(criteria.filters) && order.equals(criteria.order) && Objects.equals(limit, criteria.limit) && offset.equals(criteria.offset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filters, order, limit, offset);
    }

    public Filters filters() {
        return this.filters;
    }

    public Order order() {
        return this.order;
    }

    public Optional<Integer> limit() {
        return Optional.ofNullable(this.limit);
    }

    public Integer offset() {
        return this.offset;
    }
}
