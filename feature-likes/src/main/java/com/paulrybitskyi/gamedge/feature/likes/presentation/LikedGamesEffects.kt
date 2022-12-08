@file:Suppress("MatchingDeclarationName")

package com.paulrybitskyi.gamedge.feature.likes.presentation

import com.paulrybitskyi.gamedge.common.ui.base.events.Route

sealed class LikedGamesRoute : Route {
    object Search : LikedGamesRoute()
    data class Info(val gameId: Int) : LikedGamesRoute()
}
