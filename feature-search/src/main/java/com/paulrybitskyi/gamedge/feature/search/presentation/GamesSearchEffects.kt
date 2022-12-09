package com.paulrybitskyi.gamedge.feature.search.presentation

import ca.on.hojat.gamenews.shared.ui.base.events.Command
import ca.on.hojat.gamenews.shared.ui.base.events.Route

internal sealed class GamesSearchCommand : Command {
    object ClearItems : GamesSearchCommand()
}

sealed class GamesSearchRoute : Route {
    data class Info(val gameId: Int) : GamesSearchRoute()
    object Back : GamesSearchRoute()
}
