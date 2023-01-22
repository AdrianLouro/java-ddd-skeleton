package alouro.shared.domain.dependency_injection;

public interface Container {
    <T> T get(final Class<T> dependencyClass);
}
