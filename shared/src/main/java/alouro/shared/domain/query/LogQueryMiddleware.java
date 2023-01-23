package alouro.shared.domain.query;

import alouro.shared.domain.Logger;
import alouro.shared.domain.middleware.Middleware;

public final class LogQueryMiddleware extends Middleware<Query, Response> {

    private final Logger logger;

    public LogQueryMiddleware(final Logger logger) {
        this.logger = logger;
    }

    @Override
    public Response handle(final Query query) {
        final var queryName = query.getClass().getSimpleName();


        try {
            this.logger.info(String.format("Query <%s> received", queryName));
            final var response = this.handleNextMiddleware(query);
            this.logger.info(String.format("Query <%s> executed", queryName));

            return response;
        } catch (Exception exception) {
            this.logger.critical(String.format("Query <%s> failed: %s", queryName, exception.getMessage()));

            throw exception;
        }
    }
}
