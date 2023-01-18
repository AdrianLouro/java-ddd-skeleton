package alouro.users;

import alouro.domain.Clock;
import alouro.domain.DomainEvent;
import alouro.domain.DomainEventPublisher;
import alouro.users.domain.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

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

    protected final void shouldFind(final UserId id, final User user) {
        when(this.userRepository().find(id)).thenReturn(user);
    }

    protected final void shouldNotFindUser() {
        this.shouldFind(UserIdObjectMother.random(), null);
    }

    protected final void shouldSave(final User user) {
        verify(this.userRepository(), times(1)).save(user);
    }
}