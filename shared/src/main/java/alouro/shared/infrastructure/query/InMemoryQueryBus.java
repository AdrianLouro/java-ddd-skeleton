package alouro.shared.infrastructure.query;

import alouro.shared.domain.middleware.Middleware;
import alouro.shared.domain.middleware.MiddlewareChain;
import alouro.shared.domain.query.Query;
import alouro.shared.domain.query.QueryBus;

public final class InMemoryQueryBus implements QueryBus {

    private final MiddlewareChain middlewareChain;

    public InMemoryQueryBus(final Middleware... middlewares) {
        this.middlewareChain = MiddlewareChain.link(middlewares);
    }

    @Override
    public <R> R ask(final Query query) {
        return (R) this.middlewareChain.handle(query);
    }
}
