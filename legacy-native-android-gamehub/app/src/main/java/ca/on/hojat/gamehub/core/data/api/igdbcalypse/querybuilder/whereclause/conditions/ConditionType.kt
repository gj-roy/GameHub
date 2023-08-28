package ca.on.hojat.gamehub.core.data.api.igdbcalypse.querybuilder.whereclause.conditions

enum class ConditionType(val separator: String) {
    AND(" & "),
    OR(" | ")
}
