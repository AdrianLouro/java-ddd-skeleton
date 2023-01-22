package alouro.shared.domain.command;

public interface CommandHandler<T extends Command> {
    void handle(final T command);
}
