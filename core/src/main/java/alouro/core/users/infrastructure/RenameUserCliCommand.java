package alouro.core.users.infrastructure;

import alouro.core.users.application.rename.RenameUserCommand;
import alouro.shared.domain.command.CommandBus;
import alouro.shared.infrastructure.CliCommand;

import java.util.Map;

public final class RenameUserCliCommand extends CliCommand<Void> {

    private final CommandBus commandBus;

    public RenameUserCliCommand(final CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public Void execute(final Map<String, String> args) {
        this.commandBus.dispatch(
                new RenameUserCommand(
                        args.get("id"),
                        args.get("name")
                )
        );

        return null;
    }
}
