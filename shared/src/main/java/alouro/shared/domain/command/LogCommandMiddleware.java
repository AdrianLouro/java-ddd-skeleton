package alouro.shared.domain.command;

import alouro.shared.domain.Logger;
import alouro.shared.domain.middleware.Middleware;

public final class LogCommandMiddleware extends Middleware<Command, Void> {

    private final Logger logger;

    public LogCommandMiddleware(final Logger logger) {
        this.logger = logger;
    }

    @Override
    public Void handle(final Command command) {
        final var commandName = command.getClass().getSimpleName();

        try {
            this.logger.info(String.format("Command <%s> received", commandName));
            this.handleNextMiddleware(command);
            this.logger.info(String.format("Command <%s> executed", commandName));
        } catch (Exception exception) {
            this.logger.critical(String.format("Command <%s> failed: %s", commandName, exception.getMessage()));

            throw exception;
        }

        return null;
    }
}
