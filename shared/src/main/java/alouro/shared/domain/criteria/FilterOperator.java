package alouro.shared.domain.criteria;

public enum FilterOperator {
    EQUAL("="),
    NOT_EQUAL("!="),
    GT(">"),
    LT("<"),
    CONTAINS("CONTAINS"),
    NOT_CONTAINS("NOT_CONTAINS");

    private final String operator;

    FilterOperator(final String operator) {
        this.operator = operator;
    }

    public static FilterOperator from(final String value) {
        return switch (value) {
            case "=" -> FilterOperator.EQUAL;
            case "!=" -> FilterOperator.NOT_EQUAL;
            case ">" -> FilterOperator.GT;
            case "<" -> FilterOperator.LT;
            case "CONTAINS" -> FilterOperator.CONTAINS;
            case "NOT_CONTAINS" -> FilterOperator.NOT_CONTAINS;
            default -> null;
        };
    }

    public String value() {
        return this.operator;
    }
}
