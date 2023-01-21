package alouro.domain.command;

public interface CommandBus {
    void dispatch(final Command command);
}
