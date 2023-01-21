package alouro.infrastructure.dependency_injection;

import alouro.domain.dependency_injection.Container;
import alouro.domain.dependency_injection.CouldNotBuildDependencyException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public abstract class MyDependencyInjectionContainer implements Container {

    private final Map<Class<?>, Object> dependencies = new HashMap<>();

    private final Map<Class<?>, Callable<?>> dependenciesBuilders;

    protected MyDependencyInjectionContainer() {
        this.dependenciesBuilders = this.dependenciesBuilders();
    }

    protected abstract Map<Class<?>, Callable<?>> dependenciesBuilders();

    @Override
    public <T> T get(final Class<T> dependencyClass) {
        try {
            if (!this.dependencies.containsKey(dependencyClass)) {
                this.dependencies.put(dependencyClass, this.dependenciesBuilders.get(dependencyClass).call());
            }

            return (T) dependencies.get(dependencyClass);
        } catch (Exception exception) {
            throw new CouldNotBuildDependencyException(dependencyClass.getCanonicalName());
        }
    }
}
