package alouro.shared.domain.command;

import alouro.shared.domain.dependency_injection.Container;
import alouro.shared.domain.middleware.Middleware;

public final class HandleCommandMiddleware extends Middleware<Command, Void> {

    private final Container container;

    public HandleCommandMiddleware(final Container container) {
        this.container = container;
    }

    @Override
    public Void handle(final Command command) {
        final var commandHandlerClassName = command.getClass().getCanonicalName() + "Handler";

        try {
            final var commandHandler = (CommandHandler) this.container.get(Class.forName(commandHandlerClassName));

            commandHandler.handle(command);

            this.handleNextMiddleware(command);
        } catch (ClassNotFoundException exception) {
            throw new CommandHandlerNotFoundException(commandHandlerClassName);
        }

        return null;
    }
}
