package alouro.core.users.application.create;

import alouro.shared.domain.command.Command;

public final class CreateUserCommand extends Command {
    private final String id;
    private final String name;
    private final String birthDate;

    public CreateUserCommand(final String id, final String name, final String birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String birthDate() {
        return birthDate;
    }
}