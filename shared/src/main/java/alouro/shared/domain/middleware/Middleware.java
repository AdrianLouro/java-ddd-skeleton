package alouro.shared.domain.middleware;

import java.util.Optional;

public abstract class Middleware<C, R> {
    private Middleware<Object, R> next;

    public abstract R handle(final C context);

    public void linkNext(final Middleware<Object, R> middleware) {
        this.next = middleware;
    }

    public Optional<Middleware<Object, R>> next() {
        return Optional.ofNullable(next);
    }

    public R handleNextMiddleware(final C context) {
        return this.next()
                .map(next -> next.handle(context))
                .orElse(null);
    }
}
