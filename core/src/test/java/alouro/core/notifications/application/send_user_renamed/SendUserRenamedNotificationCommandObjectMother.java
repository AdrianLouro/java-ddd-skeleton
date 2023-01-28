package alouro.core.notifications.application.send_user_renamed;

public final class SendUserRenamedNotificationCommandObjectMother {
    public static SendUserRenamedNotificationCommand create(final String id) {
        return new SendUserRenamedNotificationCommand(id);
    }
}
