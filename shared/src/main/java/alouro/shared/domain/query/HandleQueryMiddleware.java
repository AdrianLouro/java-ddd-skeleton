package alouro.shared.domain.query;

import alouro.shared.domain.dependency_injection.Container;
import alouro.shared.domain.middleware.Middleware;

public final class HandleQueryMiddleware extends Middleware<Query, Response> {

    private final Container container;

    public HandleQueryMiddleware(final Container container) {
        this.container = container;
    }

    @Override
    public Response handle(final Query query) {
        final var queryHandlerClassName = query.getClass().getCanonicalName() + "Handler";

        try {
            final var queryHandler = (QueryHandler) this.container.get(Class.forName(queryHandlerClassName));
            final var response = queryHandler.handle(query);

            this.handleNextMiddleware(query);

            return response;
        } catch (ClassNotFoundException exception) {
            throw new QueryHandlerNotFoundException(queryHandlerClassName);
        }
    }
}
