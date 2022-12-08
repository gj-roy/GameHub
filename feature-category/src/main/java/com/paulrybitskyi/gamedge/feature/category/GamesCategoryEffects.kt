@file:Suppress("MatchingDeclarationName")

package com.paulrybitskyi.gamedge.feature.category

import com.paulrybitskyi.gamedge.common.ui.base.events.Route

sealed class GamesCategoryRoute : Route {
    data class Info(val gameId: Int) : GamesCategoryRoute()
    object Back : GamesCategoryRoute()
}
