package ca.on.hojat.gamenews.feature_info.presentation

import ca.on.hojat.gamenews.common_ui.base.events.Command
import ca.on.hojat.gamenews.common_ui.base.events.Route

internal sealed class InfoScreenCommand : Command {
    data class OpenUrl(val url: String) : InfoScreenCommand()
}

sealed class InfoScreenRoute : Route {
    data class InfoScreen(val id: Int) : InfoScreenRoute()

    data class ImageViewer(
        val title: String?,
        val initialPosition: Int,
        val imageUrls: List<String>
    ) : InfoScreenRoute()

    object Back : InfoScreenRoute()
}
