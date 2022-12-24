package ca.on.hojat.gamenews.core.data.api.igdbcalypse.querybuilder.whereclause.conditions

enum class ConditionType(val separator: String) {
    AND(" & "),
    OR(" | ")
}
