package alouro.infrastructure.dependency_injection;

import alouro.domain.dependency_injection.Container;

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
    public <T> T get(final Class<T> id) {
        try {
            if (!this.dependencies.containsKey(id)) {
                this.dependencies.put(id, this.dependenciesBuilders.get(id).call());
            }

            return (T) dependencies.get(id);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
