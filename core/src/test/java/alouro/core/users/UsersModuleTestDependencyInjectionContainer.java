package alouro.core.users;

import alouro.core.notifications.application.send_user_renamed.SendUserRenamedNotificationCommandHandler;
import alouro.core.notifications.application.send_user_renamed.SendUserRenamedNotificationOnUserRenamedEventSubscriber;
import alouro.core.users.application.create.CreateUserCommandHandler;
import alouro.core.users.application.create.UserCreator;
import alouro.core.users.application.find.FindUserQueryHandler;
import alouro.core.users.application.find.UserFinder;
import alouro.core.users.application.rename.RenameUserCommandHandler;
import alouro.core.users.application.rename.UserRenamer;
import alouro.core.users.application.search_elder.ElderUsersSearcher;
import alouro.core.users.application.search_elder.SearchElderUsersQueryHandler;
import alouro.core.users.domain.UserRenamedDomainEvent;
import alouro.core.users.domain.UserRepository;
import alouro.core.users.infrastructure.persistence.InMemoryUserRepository;
import alouro.shared.domain.Clock;
import alouro.shared.domain.Logger;
import alouro.shared.domain.command.CommandBus;
import alouro.shared.domain.command.HandleCommandMiddleware;
import alouro.shared.domain.command.LogCommandMiddleware;
import alouro.shared.domain.event.DomainEvent;
import alouro.shared.domain.event.DomainEventSubscriber;
import alouro.shared.domain.event.EventBus;
import alouro.shared.domain.query.HandleQueryMiddleware;
import alouro.shared.domain.query.LogQueryMiddleware;
import alouro.shared.domain.query.QueryBus;
import alouro.shared.infrastructure.StandardOutputLogger;
import alouro.shared.infrastructure.SystemClock;
import alouro.shared.infrastructure.command.InMemoryCommandBus;
import alouro.shared.infrastructure.dependency_injection.MyDependencyInjectionContainer;
import alouro.shared.infrastructure.event.InMemoryEventBus;
import alouro.shared.infrastructure.query.InMemoryQueryBus;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;


public final class UsersModuleTestDependencyInjectionContainer extends MyDependencyInjectionContainer {

    @Override
    protected Map<Class<?>, Callable<Object>> dependenciesResolvers() {
        return Map.ofEntries(
                new SimpleImmutableEntry<>(
                        Clock.class,
                        () -> new SystemClock()
                ),

                new SimpleImmutableEntry<>(
                        Logger.class,
                        () -> new StandardOutputLogger()
                ),

                new SimpleImmutableEntry<>(
                        UserRepository.class,
                        () -> new InMemoryUserRepository(
                                this.get(Clock.class)
                        )
                ),

                new SimpleImmutableEntry<>(
                        HandleCommandMiddleware.class,
                        () -> new HandleCommandMiddleware(this)
                ),

                new SimpleImmutableEntry<>(
                        HandleQueryMiddleware.class,
                        () -> new HandleQueryMiddleware(this)
                ),

                new SimpleImmutableEntry<>(
                        LogCommandMiddleware.class,
                        () -> new LogCommandMiddleware(
                                this.get(Logger.class)
                        )
                ),

                new SimpleImmutableEntry<>(
                        LogQueryMiddleware.class,
                        () -> new LogQueryMiddleware(
                                this.get(Logger.class)
                        )
                ),

                new SimpleImmutableEntry<>(
                        EventBus.class,
                        () -> new InMemoryEventBus(this, this.getDomainEventsSubscriptions())
                ),

                new SimpleImmutableEntry<>(
                        CommandBus.class,
                        () -> new InMemoryCommandBus(
                                this.get(LogCommandMiddleware.class),
                                this.get(HandleCommandMiddleware.class)
                        )
                ),

                new SimpleImmutableEntry<>(
                        QueryBus.class,
                        () -> new InMemoryQueryBus(
                                this.get(LogQueryMiddleware.class),
                                this.get(HandleQueryMiddleware.class)
                        )
                ),

                new SimpleImmutableEntry<>(
                        alouro.core.users.domain.UserFinder.class,
                        () -> new alouro.core.users.domain.UserFinder(
                                this.get(UserRepository.class)
                        )
                ),

                new SimpleImmutableEntry<>(
                        UserCreator.class,
                        () -> new UserCreator(
                                this.get(UserRepository.class),
                                this.get(EventBus.class),
                                this.get(Clock.class)
                        )
                ),

                new SimpleImmutableEntry<>(
                        UserRenamer.class,
                        () -> new UserRenamer(
                                this.get(UserRepository.class),
                                this.get(EventBus.class)
                        )
                ),

                new SimpleImmutableEntry<>(
                        UserFinder.class,
                        () -> new UserFinder(
                                this.get(alouro.core.users.domain.UserFinder.class)
                        )
                ),

                new SimpleImmutableEntry<>(
                        ElderUsersSearcher.class,
                        () -> new ElderUsersSearcher(
                                this.get(UserRepository.class),
                                this.get(Clock.class)
                        )
                ),

                new SimpleImmutableEntry<>(
                        CreateUserCommandHandler.class,
                        () -> new CreateUserCommandHandler(this.get(UserCreator.class))
                ),

                new SimpleImmutableEntry<>(
                        RenameUserCommandHandler.class,
                        () -> new RenameUserCommandHandler(this.get(UserRenamer.class))
                ),

                new SimpleImmutableEntry<>(
                        FindUserQueryHandler.class,
                        () -> new FindUserQueryHandler(this.get(UserFinder.class))
                ),

                new SimpleImmutableEntry<>(
                        SearchElderUsersQueryHandler.class,
                        () -> new SearchElderUsersQueryHandler(this.get(ElderUsersSearcher.class))
                ),

                new SimpleImmutableEntry<>(
                        SendUserRenamedNotificationOnUserRenamedEventSubscriber.class,
                        () -> new SendUserRenamedNotificationOnUserRenamedEventSubscriber(this.get(CommandBus.class))
                ),

                new SimpleImmutableEntry<>(
                        SendUserRenamedNotificationCommandHandler.class,
                        () -> new SendUserRenamedNotificationCommandHandler()
                )
        );
    }

    private Map<Class<? extends DomainEvent>, List<Class<? extends DomainEventSubscriber>>> getDomainEventsSubscriptions() {
        return Map.ofEntries(
                new SimpleImmutableEntry<>(
                        UserRenamedDomainEvent.class,
                        List.of(
                                SendUserRenamedNotificationOnUserRenamedEventSubscriber.class
                        )
                )
        );
    }
}
