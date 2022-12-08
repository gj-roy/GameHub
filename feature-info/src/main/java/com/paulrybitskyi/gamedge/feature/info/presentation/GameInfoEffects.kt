package com.paulrybitskyi.gamedge.feature.info.presentation

import com.paulrybitskyi.gamedge.common.ui.base.events.Command
import com.paulrybitskyi.gamedge.common.ui.base.events.Route

internal sealed class GameInfoCommand : Command {
    data class OpenUrl(val url: String) : GameInfoCommand()
}

sealed class GameInfoRoute : Route {
    data class Info(val gameId: Int) : GameInfoRoute()

    data class ImageViewer(
        val title: String?,
        val initialPosition: Int,
        val imageUrls: List<String>
    ) : GameInfoRoute()

    object Back : GameInfoRoute()
}
