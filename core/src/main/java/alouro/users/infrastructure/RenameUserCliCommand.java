package alouro.users.infrastructure;

import alouro.domain.command.CommandBus;
import alouro.infrastructure.CliCommand;
import alouro.users.application.rename.RenameUserCommand;

import java.util.Map;

public final class RenameUserCliCommand extends CliCommand {

    private final CommandBus commandBus;

    public RenameUserCliCommand(final CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void execute(final Map<String, String> args) {
        this.commandBus.dispatch(
                new RenameUserCommand(
                        args.get("id"),
                        args.get("name")
                )
        );
    }
}
