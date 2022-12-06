package ca.on.hojat.gamenews.api.igdbcalypse.querybuilder.whereclause

import ca.on.hojat.gamenews.api.igdbcalypse.querybuilder.whereclause.conditions.ConditionBuilderFactory

internal object WhereClauseBuilderFactory {

    fun newBuilder(): WhereClauseBuilder {
        return WhereClauseBuilderImpl(
            conditionBuilderFactory = createConditionBuilderFactory()
        )
    }

    private fun createConditionBuilderFactory(): ConditionBuilderFactory {
        return ConditionBuilderFactory
    }
}
