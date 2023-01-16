package alouro.infrastructure;

import alouro.domain.Clock;

import java.time.LocalDateTime;

public final class SystemClock implements Clock {
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
