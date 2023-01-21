package alouro.users.application.rename;

import alouro.domain.command.Command;

public final class RenameUserCommand extends Command {
    private final String id;
    private final String name;

    public RenameUserCommand(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }
}
