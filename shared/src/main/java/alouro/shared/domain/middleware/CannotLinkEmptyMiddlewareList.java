package alouro.shared.domain.middleware;

public final class CannotLinkEmptyMiddlewareList extends RuntimeException {
    @Override
    public String getMessage() {
        return "Cannot link an empty middleware list";
    }
}
