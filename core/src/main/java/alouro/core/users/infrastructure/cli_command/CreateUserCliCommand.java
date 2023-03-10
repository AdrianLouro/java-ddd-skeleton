package alouro.core.users.infrastructure.cli_command;

import alouro.core.users.application.create.CreateUserCommand;
import alouro.shared.domain.command.CommandBus;
import alouro.shared.infrastructure.CliCommand;

import java.util.Map;

public final class CreateUserCliCommand extends CliCommand<Void> {

    private final CommandBus commandBus;

    public CreateUserCliCommand(final CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public Void execute(final Map<String, String> args) {
        this.commandBus.dispatch(
                new CreateUserCommand(
                        args.get("id"),
                        args.get("name"),
                        args.get("birthDate")
                )
        );

        return null;
    }
}
