package alouro.users.infrastructure;

import alouro.infrastructure.CliCommand;
import alouro.users.application.UserCreator;

import java.util.Map;

public final class CreateUserCliCommand extends CliCommand {

    private final UserCreator creator;

    public CreateUserCliCommand(final UserCreator userCreator) {
        this.creator = userCreator;
    }

    @Override
    public void execute(final Map<String, String> args) {

        this.creator.create(
                args.get("id"),
                args.get("name"),
                args.get("birthDate")
        );
    }
}
