package alouro.infrastructure;

import alouro.domain.Logger;

public class StandardOutputLogger implements Logger {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_WHITE = "\u001B[37m";

    @Override
    public void info(String text) {
        this.print(text, ANSI_WHITE);
    }

    @Override
    public void critical(String text) {
        this.print(text, ANSI_RED);
    }

    private void print(final String text, final String color) {
        System.out.printf("%s%s%s%n", color, text, ANSI_RESET);
    }
}
