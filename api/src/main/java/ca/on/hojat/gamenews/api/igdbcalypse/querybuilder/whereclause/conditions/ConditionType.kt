package ca.on.hojat.gamenews.api.igdbcalypse.querybuilder.whereclause.conditions

internal enum class ConditionType(val separator: String) {
    AND(" & "),
    OR(" | ")
}
