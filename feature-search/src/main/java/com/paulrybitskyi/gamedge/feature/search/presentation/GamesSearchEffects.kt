package com.paulrybitskyi.gamedge.feature.search.presentation

import com.paulrybitskyi.gamedge.common.ui.base.events.Command
import com.paulrybitskyi.gamedge.common.ui.base.events.Route

internal sealed class GamesSearchCommand : Command {
    object ClearItems : GamesSearchCommand()
}

sealed class GamesSearchRoute : Route {
    data class Info(val gameId: Int) : GamesSearchRoute()
    object Back : GamesSearchRoute()
}
