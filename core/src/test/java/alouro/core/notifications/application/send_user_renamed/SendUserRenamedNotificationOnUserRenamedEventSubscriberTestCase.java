package alouro.core.notifications.application.send_user_renamed;

import alouro.core.notifications.NotificationsModuleUnitTestCase;
import alouro.core.users.domain.UserRenamedDomainEventObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class SendUserRenamedNotificationOnUserRenamedEventSubscriberTestCase extends NotificationsModuleUnitTestCase {
    private SendUserRenamedNotificationOnUserRenamedEventSubscriber eventSubscriber;

    @BeforeEach
    void setUp() {
        this.eventSubscriber = new SendUserRenamedNotificationOnUserRenamedEventSubscriber(
                this.commandBus().mock()
        );
    }

    @Test
    void should_send_a_notification() {
        final var event = UserRenamedDomainEventObjectMother.random();

        this.eventSubscriber.dispatch(event);

        this.commandBus().shouldHaveDispatched(SendUserRenamedNotificationCommandObjectMother.create(event.aggregateId()));
    }
}