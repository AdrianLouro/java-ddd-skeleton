package alouro.shared.domain.criteria;

import java.util.Optional;

public final class CriteriaBuilder {
    private Filters filters;
    private Order order;
    private Integer limit;
    private Integer offset;

    private CriteriaBuilder(final Filters filters, final Order order, final Integer limit, final Integer offset) {
        this.filters = filters;
        this.order = order;
        this.limit = limit;
        this.offset = Optional.ofNullable(offset).orElse(0);
    }

    public static CriteriaBuilder empty() {
        return new CriteriaBuilder(
                Filters.none(),
                Order.none(),
                null,
                0
        );
    }

    public CriteriaBuilder withFilters(final Filters filters) {
        this.filters = filters;

        return this;
    }

    public CriteriaBuilder withOrder(final Order order) {
        this.order = order;

        return this;
    }

    public CriteriaBuilder withLimit(final Integer limit) {
        this.limit = limit;

        return this;
    }

    public CriteriaBuilder withOffset(final Integer offset) {
        this.offset = offset;

        return this;
    }

    public Criteria build() {
        return new Criteria(this.filters, this.order, this.limit, this.offset);
    }
}
