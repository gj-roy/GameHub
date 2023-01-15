package ca.on.hojat.gamenews.core.data.api.igdbcalypse.querybuilder

import ca.on.hojat.gamenews.core.data.api.igdbcalypse.querybuilder.whereclause.WhereClauseBuilderFactory


object ApicalypseQueryBuilderFactory {

    fun create(): ApicalypseQueryBuilder {
        return ApicalypseQueryBuilderImpl(
            whereClauseBuilderFactory = WhereClauseBuilderFactory
        )
    }
}
