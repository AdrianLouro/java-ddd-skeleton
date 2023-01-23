package alouro.shared.domain.middleware;

public final class MiddlewareChain {
    private final Middleware<Object, Object> first;

    private MiddlewareChain(final Middleware<Object, Object> first) {
        this.first = first;
    }

    public static MiddlewareChain link(final Middleware<Object, Object>[] middlewares) {
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

    public Object handle(final Object context) {
        return first.handle(context);
    }
}
