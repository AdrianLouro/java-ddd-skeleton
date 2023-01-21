package alouro.domain.dependency_injection;

public interface Container {
    <T> T get(final Class<T> id);
}
