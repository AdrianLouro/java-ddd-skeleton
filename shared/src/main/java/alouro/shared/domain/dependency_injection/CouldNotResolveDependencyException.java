package alouro.shared.domain.dependency_injection;

public final class CouldNotResolveDependencyException extends RuntimeException {

    private final String dependencyClassName;

    public CouldNotResolveDependencyException(final String dependencyClassName) {
        super();

        this.dependencyClassName = dependencyClassName;
    }

    @Override
    public String getMessage() {
        return "Could not resolve dependency: " + this.dependencyClassName;
    }
}
