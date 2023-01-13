package ca.on.hojat.gamenews.feature_info.presentation

import ca.on.hojat.gamenews.common_ui.base.events.Command
import ca.on.hojat.gamenews.common_ui.base.events.Route

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
