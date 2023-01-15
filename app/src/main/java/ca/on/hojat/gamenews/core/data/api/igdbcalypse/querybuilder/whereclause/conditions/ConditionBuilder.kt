package ca.on.hojat.gamenews.core.data.api.igdbcalypse.querybuilder.whereclause.conditions

import ca.on.hojat.gamenews.core.data.api.igdbcalypse.querybuilder.whereclause.Condition

 interface ConditionBuilder {
    fun condition(condition: Condition): ConditionBuilder
    fun build(): String
}
