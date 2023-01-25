package alouro.shared.domain.criteria;

import java.util.Objects;
import java.util.Optional;

import static alouro.shared.domain.criteria.OrderType.*;

public final class Order {
    private final OrderBy orderBy;
    private final OrderType orderType;

    private Order(final OrderBy orderBy, final OrderType orderType) {
        this.orderBy = orderBy;
        this.orderType = orderType;
    }

    public static Order fromPrimitives(final String orderBy, final String orderType) {
        final var definitiveOrderType = Optional.ofNullable(orderType).orElse(ASC.value()).toUpperCase();

        return Optional.ofNullable(orderBy)
                .map(order -> new Order(
                                new OrderBy(order),
                                valueOf(definitiveOrderType)
                        )
                )
                .orElseGet(Order::none);
    }

    public static Order none() {
        return new Order(new OrderBy(""), NONE);
    }

    public static Order desc(final String orderBy) {
        return new Order(new OrderBy(orderBy), DESC);
    }

    public static Order asc(final String orderBy) {
        return new Order(new OrderBy(orderBy), ASC);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderBy.equals(order.orderBy) && orderType == order.orderType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderBy, orderType);
    }

    public OrderBy orderBy() {
        return orderBy;
    }

    public OrderType orderType() {
        return orderType;
    }

    public boolean hasOrder() {
        return !orderType.isNone();
    }
}
