@file:Suppress("MatchingDeclarationName")

package ca.on.hojat.gamenews.feature_likes.presentation

import ca.on.hojat.gamenews.core.common_ui.base.events.Route

sealed class LikedGamesRoute : Route {
    object Search : LikedGamesRoute()
    data class Info(val gameId: Int) : LikedGamesRoute()
}
