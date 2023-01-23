package alouro.shared.infrastructure.command;

import alouro.shared.domain.command.Command;
import alouro.shared.domain.command.CommandBus;
import alouro.shared.domain.middleware.Middleware;
import alouro.shared.domain.middleware.MiddlewareChain;

public final class InMemoryCommandBus implements CommandBus {

    private final MiddlewareChain middlewareChain;

    public InMemoryCommandBus(final Middleware... middlewares) {
        this.middlewareChain = MiddlewareChain.link(middlewares);
    }

    @Override
    public void dispatch(final Command command) {
        this.middlewareChain.handle(command);
    }
}
