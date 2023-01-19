package alouro.users.application;

import alouro.domain.Clock;
import alouro.domain.DomainEvent;
import alouro.domain.DomainEventPublisher;
import alouro.users.domain.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.Random.class)
public abstract class UsersModuleUnitTestCase {
    private Clock clock;
    private DomainEventPublisher domainEventPublisher;
    private UserRepository userRepository;

    protected final Clock clock() {
        if (this.clock == null) {
            this.clock = mock(Clock.class);
        }

        return this.clock;
    }

    protected final DomainEventPublisher domainEventPublisher() {
        if (this.domainEventPublisher == null) {
            this.domainEventPublisher = mock(DomainEventPublisher.class);
        }

        return this.domainEventPublisher;
    }

    protected final UserRepository userRepository() {
        if (this.userRepository == null) {
            this.userRepository = mock(UserRepository.class);
        }

        return this.userRepository;
    }

    protected final void shouldNowBe(final LocalDateTime localDateTime) {
        when(this.clock().now()).thenReturn(localDateTime);
    }

    protected void shouldPublish(final DomainEvent domainEvent) {
        final var domainEvents = List.of(domainEvent);
        verify(this.domainEventPublisher(), times(1)).publish(domainEvents);
    }

    protected void shouldNotPublishAnyDomainEvent() {
        verify(this.domainEventPublisher(), times(1)).publish(List.of());
    }

    protected final void shouldFindUser(final UserId id, final User user) {
        when(this.userRepository().search(id)).thenReturn(Optional.of(user));
    }

    protected final void shouldNotFindUser(final UserId id) {
        when(this.userRepository().search(id)).thenReturn(Optional.empty());
    }

    protected final void shouldSave(final User user) {
        verify(this.userRepository(), times(1)).save(user);
    }
}
