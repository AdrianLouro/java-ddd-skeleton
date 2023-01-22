package alouro.shared.domain.dependency_injection;

public final class CouldNotBuildDependencyException extends RuntimeException {

    private final String dependencyClassName;

    public CouldNotBuildDependencyException(final String dependencyClassName) {
        super();

        this.dependencyClassName = dependencyClassName;
    }

    @Override
    public String getMessage() {
        return "Could not build dependency: " + this.dependencyClassName;
    }
}
