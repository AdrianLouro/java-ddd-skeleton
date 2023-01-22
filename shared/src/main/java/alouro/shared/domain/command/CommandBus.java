package alouro.shared.domain.command;

public interface CommandBus {
    void dispatch(final Command command);
}
