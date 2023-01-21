package alouro;

import alouro.domain.ClockMock;
import alouro.domain.EventBusMock;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
public abstract class UnitTestCase {
    private ClockMock clock;
    private EventBusMock eventBus;

    protected final ClockMock clock() {
        if (this.clock == null) {
            this.clock = new ClockMock();
        }

        return this.clock;
    }

    protected final EventBusMock eventBus() {
        if (this.eventBus == null) {
            this.eventBus = new EventBusMock();
        }

        return this.eventBus;
    }
}
