package ca.on.hojat.gamenews.api.igdbcalypse.querybuilder

import ca.on.hojat.gamenews.api.igdbcalypse.querybuilder.whereclause.WhereClauseBuilderFactory

object ApicalypseQueryBuilderFactory {

    fun create(): ApicalypseQueryBuilder {
        return ApicalypseQueryBuilderImpl(
            whereClauseBuilderFactory = WhereClauseBuilderFactory
        )
    }
}
