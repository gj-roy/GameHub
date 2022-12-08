@file:Suppress("MatchingDeclarationName")

package com.paulrybitskyi.gamedge.feature.discovery

import com.paulrybitskyi.gamedge.common.ui.base.events.Route

sealed class GamesDiscoveryRoute : Route {
    object Search : GamesDiscoveryRoute()
    data class Category(val category: String) : GamesDiscoveryRoute()
    data class Info(val gameId: Int) : GamesDiscoveryRoute()
}
