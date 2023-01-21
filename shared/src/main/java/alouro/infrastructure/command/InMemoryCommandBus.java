package alouro.infrastructure.command;

import alouro.domain.command.Command;
import alouro.domain.command.CommandBus;
import alouro.domain.command.CommandHandler;
import alouro.domain.command.CommandHandlerNotFoundException;
import alouro.domain.dependency_injection.Container;

public final class InMemoryCommandBus implements CommandBus {

    private final Container container;

    public InMemoryCommandBus(final Container container) {
        this.container = container;
    }

    @Override
    public void dispatch(final Command command) {
        final var commandHandlerClassName = command.getClass().getCanonicalName() + "Handler";

        try {
            final var commandHandler = (CommandHandler) this.container.get(Class.forName(commandHandlerClassName));

            commandHandler.handle(command);
        } catch (ClassNotFoundException exception) {
            throw new CommandHandlerNotFoundException(commandHandlerClassName);
        }
    }
}
