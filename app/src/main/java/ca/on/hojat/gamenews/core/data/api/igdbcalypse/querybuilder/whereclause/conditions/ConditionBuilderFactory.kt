package ca.on.hojat.gamenews.core.data.api.igdbcalypse.querybuilder.whereclause.conditions

import ca.on.hojat.gamenews.core.data.api.igdbcalypse.querybuilder.whereclause.WhereClauseBuilderFactory

object ConditionBuilderFactory {

    fun newBuilder(conditionType: ConditionType): ConditionBuilder {
        return ConditionBuilderImpl(
            conditionType = conditionType,
            whereClauseBuilderFactory = WhereClauseBuilderFactory
        )
    }
}
