@file:Suppress("MatchingDeclarationName")

package ca.on.hojat.gamehub.feature_category

import ca.on.hojat.gamehub.core.events.Route

sealed class CategoryScreenRoute : Route {
    data class Info(val gameId: Int) : CategoryScreenRoute()
    object Back : CategoryScreenRoute()
}
