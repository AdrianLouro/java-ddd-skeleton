package alouro.domain.query;

public final class QueryHandlerNotFoundException extends RuntimeException {

    private final String queryHandlerClassName;

    public QueryHandlerNotFoundException(final String queryHandlerClassName) {
        super();

        this.queryHandlerClassName = queryHandlerClassName;
    }

    @Override
    public String getMessage() {
        return "Query handler not found: " + this.queryHandlerClassName;
    }
}
