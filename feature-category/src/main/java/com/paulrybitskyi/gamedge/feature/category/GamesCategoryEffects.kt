@file:Suppress("MatchingDeclarationName")

package com.paulrybitskyi.gamedge.feature.category

import ca.on.hojat.gamenews.shared.ui.base.events.Route

sealed class GamesCategoryRoute : Route {
    data class Info(val gameId: Int) : GamesCategoryRoute()
    object Back : GamesCategoryRoute()
}
