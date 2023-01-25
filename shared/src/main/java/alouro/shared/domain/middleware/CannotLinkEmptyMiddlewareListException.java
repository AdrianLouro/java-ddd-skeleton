package alouro.shared.domain.middleware;

public final class CannotLinkEmptyMiddlewareListException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Cannot link an empty middleware list";
    }
}
