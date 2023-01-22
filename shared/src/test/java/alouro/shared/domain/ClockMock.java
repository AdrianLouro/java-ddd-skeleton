package alouro.shared.domain;

import alouro.shared.Mock;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

public final class ClockMock extends Mock<Clock> {
    public void givenACurrentDate(final LocalDateTime localDateTime) {
        when(this.mock().now()).thenReturn(localDateTime);
    }
}
