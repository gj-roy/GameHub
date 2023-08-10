package ca.on.hojat.gamehub.feature_search.presentation

import ca.on.hojat.gamehub.core.events.Route

sealed class GamesSearchRoute : Route {
    data class Info(val gameId: Int) : GamesSearchRoute()
    object Back : GamesSearchRoute()
}
