package alouro.users.infrastructure;

import alouro.infrastructure.CliCommand;
import alouro.users.application.rename.UserRenamer;

import java.util.Map;

public final class RenameUserCliCommand extends CliCommand {

    private final UserRenamer renamer;

    public RenameUserCliCommand(final UserRenamer renamer) {
        this.renamer = renamer;
    }

    @Override
    public void execute(final Map<String, String> args) {

        this.renamer.rename(args.get("id"), args.get("name"));
    }
}
