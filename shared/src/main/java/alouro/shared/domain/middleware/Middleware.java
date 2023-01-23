package alouro.shared.domain.middleware;

import java.util.Optional;

public abstract class Middleware<T> {
    private Middleware<Object> next;

    public abstract void handle(final T context);

    public void linkNext(final Middleware<Object> middleware) {
        this.next = middleware;
    }

    public Optional<Middleware<Object>> next() {
        return Optional.ofNullable(next);
    }

    public void handleNextMiddleware(final T context) {
        this.next().ifPresent(next -> next.handle(context));
    }
}
