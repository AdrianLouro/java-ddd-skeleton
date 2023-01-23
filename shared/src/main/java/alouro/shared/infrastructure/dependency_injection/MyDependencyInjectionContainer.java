package alouro.shared.infrastructure.dependency_injection;

import alouro.shared.domain.dependency_injection.Container;
import alouro.shared.domain.dependency_injection.CouldNotResolveDependencyException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public abstract class MyDependencyInjectionContainer implements Container {

    private final Map<Class<?>, Object> dependencies = new HashMap<>();

    private final Map<Class<?>, Callable<Object>> dependenciesResolvers;

    protected MyDependencyInjectionContainer() {
        this.dependenciesResolvers = this.dependenciesResolvers();
    }

    protected abstract Map<Class<?>, Callable<Object>> dependenciesResolvers();

    @Override
    public <T> T get(final Class<T> dependencyClass) {
        try {
            if (!this.dependencies.containsKey(dependencyClass)) {
                this.dependencies.put(dependencyClass, this.dependenciesResolvers.get(dependencyClass).call());
            }

            return (T) dependencies.get(dependencyClass);
        } catch (Exception exception) {
            throw new CouldNotResolveDependencyException(dependencyClass.getCanonicalName());
        }
    }
}
