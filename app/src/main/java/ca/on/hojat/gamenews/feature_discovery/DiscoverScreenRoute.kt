@file:Suppress("MatchingDeclarationName")

package ca.on.hojat.gamenews.feature_discovery

import ca.on.hojat.gamenews.common_ui.base.events.Route

sealed class DiscoverScreenRoute : Route {
    object Search : DiscoverScreenRoute()
    data class Category(val category: String) : DiscoverScreenRoute()
    data class Info(val itemId: Int) : DiscoverScreenRoute()
}
