package alouro.users.application.rename;

import alouro.domain.command.CommandHandler;

public final class RenameUserCommandHandler implements CommandHandler<RenameUserCommand> {

    private final UserRenamer renamer;

    public RenameUserCommandHandler(final UserRenamer renamer) {
        this.renamer = renamer;
    }

    @Override
    public void handle(final RenameUserCommand command) {
        this.renamer.rename(command.id(), command.name());
    }
}