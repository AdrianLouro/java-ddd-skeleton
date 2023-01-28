package alouro.core.notifications.application.send_user_renamed;

import alouro.shared.domain.command.CommandHandler;

public final class SendUserRenamedNotificationCommandHandler implements CommandHandler<SendUserRenamedNotificationCommand> {

    public SendUserRenamedNotificationCommandHandler(/** Inject "sender" application service */) {
    }

    @Override
    public void handle(final SendUserRenamedNotificationCommand command) {
        // this.sender.send(...)
    }
}