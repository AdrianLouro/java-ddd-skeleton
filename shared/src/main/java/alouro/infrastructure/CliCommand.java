package alouro.infrastructure;

import java.util.Map;

public abstract class CliCommand {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public abstract void execute(final Map<String, String> args);

    protected void log(final String text) {
        this.print(text, ANSI_WHITE);
    }

    protected void info(final String text) {
        this.print(text, ANSI_CYAN);
    }

    protected void success(String text) {
        this.print(text, ANSI_GREEN);
    }

    protected void warning(String text) {
        this.print(text, ANSI_YELLOW);
    }

    protected void error(final String text) {
        this.print(text, ANSI_RED);
    }

    private void print(String text, String color) {
        System.out.printf("%s%s%s%n", color, text, ANSI_RESET);
    }
}
