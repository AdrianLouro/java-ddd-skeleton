package alouro.core.users;

import alouro.shared.domain.Clock;
import alouro.shared.domain.command.CommandBus;
import alouro.shared.domain.event.DomainEvent;
import alouro.shared.domain.event.EventBus;
import alouro.shared.domain.query.QueryBus;
import alouro.shared.infrastructure.SystemClock;
import alouro.shared.infrastructure.command.InMemoryCommandBus;
import alouro.shared.infrastructure.dependency_injection.MyDependencyInjectionContainer;
import alouro.shared.infrastructure.query.InMemoryQueryBus;
import alouro.core.users.application.create.CreateUserCommandHandler;
import alouro.core.users.application.create.UserCreator;
import alouro.core.users.application.find.FindUserQueryHandler;
import alouro.core.users.application.find.UserFinder;
import alouro.core.users.application.rename.RenameUserCommandHandler;
import alouro.core.users.application.rename.UserRenamer;
import alouro.core.users.domain.UserRepository;
import alouro.core.users.infrastructure.InMemoryUserRepository;

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
