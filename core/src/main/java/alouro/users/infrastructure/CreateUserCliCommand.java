package alouro.users.infrastructure;

import alouro.domain.command.CommandBus;
import alouro.infrastructure.CliCommand;
import alouro.users.application.create.CreateUserCommand;

import java.util.Map;

public final class CreateUserCliCommand extends CliCommand {

    private final CommandBus commandBus;

    public CreateUserCliCommand(final CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Override
    public void execute(final Map<String, String> args) {
        this.commandBus.dispatch(
                new CreateUserCommand(
                        args.get("id"),
                        args.get("name"),
                        args.get("birthDate")
                )
        );
    }
}
