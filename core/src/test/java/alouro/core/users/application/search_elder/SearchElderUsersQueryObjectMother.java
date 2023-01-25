package alouro.core.users.application.search_elder;

public final class SearchElderUsersQueryObjectMother {

    public static SearchElderUsersQuery create(final String orderBy, final String orderType, final Integer limit, final Integer offset) {
        return new SearchElderUsersQuery(orderBy, orderType, limit, offset);
    }
}