@file:Suppress("MatchingDeclarationName")

package com.paulrybitskyi.gamedge.feature_likes.presentation

import ca.on.hojat.gamenews.shared.ui.base.events.Route

sealed class LikedGamesRoute : Route {
    object Search : LikedGamesRoute()
    data class Info(val gameId: Int) : LikedGamesRoute()
}
