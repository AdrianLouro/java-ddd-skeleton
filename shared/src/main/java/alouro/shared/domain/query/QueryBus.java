package alouro.shared.domain.query;

public interface QueryBus {
    <R> R ask(final Query query);
}
