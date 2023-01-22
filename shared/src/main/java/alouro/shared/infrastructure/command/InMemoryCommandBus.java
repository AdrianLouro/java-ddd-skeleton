package alouro.shared.infrastructure.command;

import alouro.shared.domain.command.Command;
import alouro.shared.domain.command.CommandBus;
import alouro.shared.domain.command.CommandHandler;
import alouro.shared.domain.command.CommandHandlerNotFoundException;
import alouro.shared.domain.dependency_injection.Container;

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
