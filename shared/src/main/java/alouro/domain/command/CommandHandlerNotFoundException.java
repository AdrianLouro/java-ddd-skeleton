package alouro.domain.command;

public final class CommandHandlerNotFoundException extends RuntimeException {

    private final String commandHandlerClass;

    public CommandHandlerNotFoundException(final String commandHandlerClass) {
        super();

        this.commandHandlerClass = commandHandlerClass;
    }

    @Override
    public String getMessage() {
        return "Command handler not found: " + this.commandHandlerClass;
    }
}
