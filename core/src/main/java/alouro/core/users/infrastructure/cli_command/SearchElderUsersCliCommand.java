package alouro.core.users.infrastructure.cli_command;

import alouro.core.users.application.UsersResponse;
import alouro.core.users.application.search_elder.SearchElderUsersQuery;
import alouro.shared.domain.query.QueryBus;
import alouro.shared.infrastructure.CliCommand;

import java.util.Map;
import java.util.Optional;

public final class SearchElderUsersCliCommand extends CliCommand<UsersResponse> {

    private final QueryBus queryBus;

    public SearchElderUsersCliCommand(final QueryBus queryBus) {
        this.queryBus = queryBus;
    }

    @Override
    public UsersResponse execute(final Map<String, String> args) {
        return this.queryBus.ask(
                new SearchElderUsersQuery(
                        args.get("orderBy"),
                        args.get("orderType"),
                        Optional.ofNullable(args.get("limit")).map(Integer::valueOf).orElse(null),
                        Optional.ofNullable(args.get("offset")).map(Integer::valueOf).orElse(null)
                )
        );
    }
}
