package alouro.infrastructure;

import java.util.Map;

public abstract class CliCommand {

    public abstract void execute(final Map<String, String> args);
}
