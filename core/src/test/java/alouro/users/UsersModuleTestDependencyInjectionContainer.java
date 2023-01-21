package alouro.users;

import alouro.domain.Clock;
import alouro.domain.command.CommandBus;
import alouro.domain.event.DomainEvent;
import alouro.domain.event.EventBus;
import alouro.domain.query.QueryBus;
import alouro.infrastructure.SystemClock;
import alouro.infrastructure.command.InMemoryCommandBus;
import alouro.infrastructure.dependency_injection.MyDependencyInjectionContainer;
import alouro.infrastructure.query.InMemoryQueryBus;
import alouro.users.application.create.CreateUserCommandHandler;
import alouro.users.application.create.UserCreator;
import alouro.users.application.find.FindUserQueryHandler;
import alouro.users.application.find.UserFinder;
import alouro.users.application.rename.RenameUserCommandHandler;
import alouro.users.application.rename.UserRenamer;
import alouro.users.domain.UserRepository;
import alouro.users.infrastructure.InMemoryUserRepository;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public final class UsersModuleTestDependencyInjectionContainer extends MyDependencyInjectionContainer {

    @Override
    protected Map<Class<?>, Callable<?>> dependenciesBuilders() {
        return Map.ofEntries(
                new SimpleImmutableEntry<>(
                        Clock.class,
                        () -> new SystemClock()
                ),

                new SimpleImmutableEntry<>(
                        UserRepository.class,
                        () -> new InMemoryUserRepository()
                ),

                new SimpleImmutableEntry<>(
                        EventBus.class,
                        () -> new EventBus() {
                            @Override
                            public void publish(List<DomainEvent> events) {

                            }
                        }
                ),

                new SimpleImmutableEntry<>(
                        CommandBus.class,
                        () -> new InMemoryCommandBus(this)
                ),

                new SimpleImmutableEntry<>(
                        QueryBus.class,
                        () -> new InMemoryQueryBus(this)
                ),

                new SimpleImmutableEntry<>(
                        alouro.users.domain.UserFinder.class,
                        () -> new alouro.users.domain.UserFinder(
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
                                this.get(alouro.users.domain.UserFinder.class)
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
                )
        );
    }
}
