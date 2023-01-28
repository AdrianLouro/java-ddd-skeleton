package alouro.shared;

import alouro.shared.domain.ClockMock;
import alouro.shared.domain.CommandBusMock;
import alouro.shared.domain.EventBusMock;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
public abstract class UnitTestCase {
    private ClockMock clock;
    private EventBusMock eventBus;
    private CommandBusMock commandBus;

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

    protected final CommandBusMock commandBus() {
        if (this.commandBus == null) {
            this.commandBus = new CommandBusMock();
        }

        return this.commandBus;
    }
}
