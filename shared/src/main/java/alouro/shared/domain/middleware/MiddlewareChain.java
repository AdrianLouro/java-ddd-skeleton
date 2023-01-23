package alouro.shared.domain.middleware;

public final class MiddlewareChain {
    private final Middleware<Object> first;

    private MiddlewareChain(final Middleware<Object> first) {
        this.first = first;
    }

    public static MiddlewareChain link(final Middleware<Object>[] middlewares) {
        if (middlewares.length == 0) {
            throw new CannotLinkEmptyMiddlewareList();
        }

        for (int index = 0; index < middlewares.length - 1; index++) {
            final var currentMiddleware = middlewares[index];
            final var nextMiddleware = middlewares[index + 1];

            currentMiddleware.linkNext(nextMiddleware);
        }

        return new MiddlewareChain(middlewares[0]);
    }

    public void invoke(final Object context) {
        first.handle(context);
    }
}
