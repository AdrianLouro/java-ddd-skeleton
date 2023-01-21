package alouro.domain.query;

public interface QueryHandler<Q extends Query, R extends Response> {
    R handle(final Q query);
}
