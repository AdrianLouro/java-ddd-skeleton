package alouro.users.application.create;

import alouro.domain.command.CommandHandler;

public final class CreateUserCommandHandler implements CommandHandler<CreateUserCommand> {

    private final UserCreator creator;

    public CreateUserCommandHandler(final UserCreator creator) {
        this.creator = creator;
    }

    @Override
    public void handle(final CreateUserCommand command) {
        this.creator.create(command.id(), command.name(), command.birthDate());
    }
}