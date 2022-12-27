@file:Suppress("MatchingDeclarationName")

package ca.on.hojat.gamenews.feature_category

import ca.on.hojat.gamenews.core.common_ui.base.events.Route

sealed class GamesCategoryRoute : Route {
    data class Info(val gameId: Int) : GamesCategoryRoute()
    object Back : GamesCategoryRoute()
}
