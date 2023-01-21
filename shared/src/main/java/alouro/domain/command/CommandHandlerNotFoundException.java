package alouro.domain.command;

public final class CommandHandlerNotFoundException extends RuntimeException {

    private final String commandHandlerClassName;

    public CommandHandlerNotFoundException(final String commandHandlerClassName) {
        super();

        this.commandHandlerClassName = commandHandlerClassName;
    }

    @Override
    public String getMessage() {
        return "Command handler not found: " + this.commandHandlerClassName;
    }
}
