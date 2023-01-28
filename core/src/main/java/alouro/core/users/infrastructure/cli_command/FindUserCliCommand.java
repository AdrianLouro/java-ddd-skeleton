package alouro.core.users.infrastructure.cli_command;

import alouro.core.users.application.UserResponse;
import alouro.core.users.application.find.FindUserQuery;
import alouro.shared.domain.query.QueryBus;
import alouro.shared.infrastructure.CliCommand;

import java.util.Map;

public final class FindUserCliCommand extends CliCommand<UserResponse> {

    private final QueryBus queryBus;

    public FindUserCliCommand(final QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @Override
    public UserResponse execute(final Map<String, String> args) {
        return this.queryBus.ask(new FindUserQuery(args.get("id")));
    }
}
