package alouro.shared.domain.criteria;

public final class CriteriaObjectMother {
    public static Criteria empty() {
        return new Criteria(Filters.none(), Order.none(), null, 0);
    }
}
