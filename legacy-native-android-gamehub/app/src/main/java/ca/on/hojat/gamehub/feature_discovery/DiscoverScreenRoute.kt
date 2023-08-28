@file:Suppress("MatchingDeclarationName")

package ca.on.hojat.gamehub.feature_discovery

import ca.on.hojat.gamehub.core.events.Route

sealed class DiscoverScreenRoute : Route {
    object Search : DiscoverScreenRoute()
    data class Category(val category: String) : DiscoverScreenRoute()
    data class Info(val itemId: Int) : DiscoverScreenRoute()
}
