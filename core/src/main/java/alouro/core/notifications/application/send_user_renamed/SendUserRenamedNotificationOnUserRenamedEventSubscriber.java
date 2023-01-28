package alouro.core.notifications.application.send_user_renamed;

import alouro.core.users.domain.UserRenamedDomainEvent;
import alouro.shared.domain.command.CommandBus;
import alouro.shared.domain.event.DomainEventSubscriber;

public final class SendUserRenamedNotificationOnUserRenamedEventSubscriber implements DomainEventSubscriber<UserRenamedDomainEvent> {

    private final CommandBus commandBus;

    public SendUserRenamedNotificationOnUserRenamedEventSubscriber(final CommandBus commandBus) {
        this.commandBus = commandBus; // TODO: should event subscribers receive the command bus or the application service ???
    }

    @Override
    public void dispatch(final UserRenamedDomainEvent event) {
        this.commandBus.dispatch(new SendUserRenamedNotificationCommand(event.aggregateId()));
    }
}
