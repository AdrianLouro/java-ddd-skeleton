package alouro.shared.infrastructure;

import java.util.Map;

public abstract class CliCommand<R> {

    public abstract R execute(final Map<String, String> args);
}
