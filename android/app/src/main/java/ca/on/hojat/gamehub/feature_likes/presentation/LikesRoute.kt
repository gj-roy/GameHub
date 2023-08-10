@file:Suppress("MatchingDeclarationName")

package ca.on.hojat.gamehub.feature_likes.presentation

import ca.on.hojat.gamehub.core.events.Route

sealed class LikesRoute : Route {
    object Search : LikesRoute()
    data class Info(val gameId: Int) : LikesRoute()
}
