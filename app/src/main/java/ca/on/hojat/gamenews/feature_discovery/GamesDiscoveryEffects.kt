@file:Suppress("MatchingDeclarationName")

package ca.on.hojat.gamenews.feature_discovery

import ca.on.hojat.gamenews.common_ui.base.events.Route

sealed class GamesDiscoveryRoute : Route {
    object Search : GamesDiscoveryRoute()
    data class Category(val category: String) : GamesDiscoveryRoute()
    data class Info(val gameId: Int) : GamesDiscoveryRoute()
}
