package ca.on.hojat.gamenews.api.igdbcalypse.querybuilder.whereclause.conditions

import ca.on.hojat.gamenews.api.igdbcalypse.querybuilder.whereclause.WhereClauseBuilderFactory

internal object ConditionBuilderFactory {

    fun newBuilder(conditionType: ConditionType): ConditionBuilder {
        return ConditionBuilderImpl(
            conditionType = conditionType,
            whereClauseBuilderFactory = WhereClauseBuilderFactory
        )
    }
}
