@file:Suppress("MatchingDeclarationName")

package ca.on.hojat.gamenews.feature_likes.presentation

import ca.on.hojat.gamenews.common_ui.base.events.Route

sealed class LikesRoute : Route {
    object Search : LikesRoute()
    data class Info(val gameId: Int) : LikesRoute()
}
