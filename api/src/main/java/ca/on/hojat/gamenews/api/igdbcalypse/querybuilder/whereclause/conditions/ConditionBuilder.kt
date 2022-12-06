package ca.on.hojat.gamenews.api.igdbcalypse.querybuilder.whereclause.conditions

import ca.on.hojat.gamenews.api.igdbcalypse.querybuilder.whereclause.Condition

internal interface ConditionBuilder {
    fun condition(condition: Condition): ConditionBuilder
    fun build(): String
}
