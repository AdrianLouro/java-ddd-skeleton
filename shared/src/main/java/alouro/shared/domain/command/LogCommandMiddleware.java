package alouro.shared.domain.command;

import alouro.shared.domain.Logger;
import alouro.shared.domain.middleware.Middleware;

public final class LogCommandMiddleware extends Middleware<Command> {

    private final Logger logger;

    public LogCommandMiddleware(final Logger logger) {
        this.logger = logger;
    }

    @Override
    public void handle(final Command command) {
        final var commandName = command.getClass().getSimpleName();

        this.logger.info(String.format("Command <%s> received", commandName));

        try {
            this.handleNextMiddleware(command);
        } catch (Exception exception) {
            this.logger.critical(String.format("Command <%s> failed", commandName));
            throw exception;
        }

        this.logger.info(String.format("Command <%s> executed", commandName));
    }
}
