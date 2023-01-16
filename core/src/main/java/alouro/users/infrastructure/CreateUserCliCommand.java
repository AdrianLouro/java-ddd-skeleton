package alouro.users.infrastructure;

import alouro.domain.Clock;
import alouro.infrastructure.CliCommand;
import alouro.users.application.UserCreator;
import alouro.users.domain.UserBirthDate;
import alouro.users.domain.UserId;
import alouro.users.domain.UserName;

import java.util.Map;

public final class CreateUserCliCommand extends CliCommand {

    private final UserCreator creator;
    private final Clock clock;

    public CreateUserCliCommand(final UserCreator userCreator, final Clock clock) {
        this.creator = userCreator;
        this.clock = clock;
    }

    @Override
    public void execute(final Map<String, String> args) {
        final var id = new UserId(args.get("id"));
        final var name = new UserName(args.get("name"));
        final var birthDate = new UserBirthDate(args.get("birthDate"), this.clock);

        this.creator.create(id, name, birthDate);
    }
}
