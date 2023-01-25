package alouro.shared.domain.criteria;

public enum OrderType {
    ASC("asc"),
    DESC("desc"),
    NONE("none");

    private final String type;

    OrderType(final String type) {
        this.type = type;
    }

    public boolean isNone() {
        return this == NONE;
    }

    public String value() {
        return this.type;
    }
}
