package alouro.users.infrastructure;

import alouro.infrastructure.CliCommand;
import alouro.users.application.UserRenamer;
import alouro.users.domain.UserId;
import alouro.users.domain.UserName;

import java.util.Map;

public final class RenameUserCliCommand extends CliCommand {

    private final UserRenamer renamer;

    public RenameUserCliCommand(final UserRenamer renamer) {
        this.renamer = renamer;
    }

    @Override
    public void execute(final Map<String, String> args) {
        final var id = new UserId(args.get("id"));
        final var name = new UserName(args.get("name"));

        this.renamer.rename(id, name);
    }
}
