package alouro.users.domain;

import alouro.domain.event.DomainEvent;

import java.util.Objects;

public final class UserRenamedDomainEvent extends DomainEvent {
    private final String id;
    private final String name;

    public UserRenamedDomainEvent(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRenamedDomainEvent that = (UserRenamedDomainEvent) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }
}
