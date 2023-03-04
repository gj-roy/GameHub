package ca.on.hojat.gamenews.feature_search.presentation

import ca.on.hojat.gamenews.core.events.Route

sealed class GamesSearchRoute : Route {
    data class Info(val gameId: Int) : GamesSearchRoute()
    object Back : GamesSearchRoute()
}
