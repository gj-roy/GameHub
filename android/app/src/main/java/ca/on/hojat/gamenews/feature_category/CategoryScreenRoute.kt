@file:Suppress("MatchingDeclarationName")

package ca.on.hojat.gamenews.feature_category

import ca.on.hojat.gamenews.core.events.Route

sealed class CategoryScreenRoute : Route {
    data class Info(val gameId: Int) : CategoryScreenRoute()
    object Back : CategoryScreenRoute()
}
