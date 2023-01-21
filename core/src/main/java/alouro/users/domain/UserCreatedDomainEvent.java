package alouro.users.domain;

import alouro.domain.event.DomainEvent;

import java.util.Objects;

public final class UserCreatedDomainEvent extends DomainEvent {
    private final String id;
    private final String name;
    private final String birthDate;

    public UserCreatedDomainEvent(final String id, final String name, final String birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreatedDomainEvent that = (UserCreatedDomainEvent) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String birthDate() {
        return birthDate;
    }
}
