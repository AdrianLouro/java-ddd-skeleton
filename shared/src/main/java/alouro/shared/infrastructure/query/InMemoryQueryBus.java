package alouro.shared.infrastructure.query;

import alouro.shared.domain.dependency_injection.Container;
import alouro.shared.domain.query.Query;
import alouro.shared.domain.query.QueryBus;
import alouro.shared.domain.query.QueryHandler;
import alouro.shared.domain.query.QueryHandlerNotFoundException;

public final class InMemoryQueryBus implements QueryBus {

    private final Container container;

    public InMemoryQueryBus(final Container container) {
        this.container = container;
    }

    @Override
    public <R> R ask(Query query) {
        final var queryHandlerClassName = query.getClass().getCanonicalName() + "Handler";

        try {
            final var queryHandler = (QueryHandler) this.container.get(Class.forName(queryHandlerClassName));

            return (R) queryHandler.handle(query);
        } catch (ClassNotFoundException exception) {
            throw new QueryHandlerNotFoundException(queryHandlerClassName);
        }
    }
}