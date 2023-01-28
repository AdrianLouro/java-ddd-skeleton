package alouro.shared.domain;

import alouro.shared.Mock;
import alouro.shared.domain.command.Command;
import alouro.shared.domain.command.CommandBus;

import static org.mockito.Mockito.*;

public final class CommandBusMock extends Mock<CommandBus> {

    public void shouldHaveDispatched(final Command command) {
        verify(this.mock(), times(1)).dispatch(command);
    }

    public void shouldHaveNotDispatchedAnyCommand() {
        verify(this.mock(), never()).dispatch(any());
    }
}
