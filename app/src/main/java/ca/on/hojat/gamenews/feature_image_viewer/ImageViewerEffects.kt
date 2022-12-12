package ca.on.hojat.gamenews.feature_image_viewer

import ca.on.hojat.gamenews.shared.ui.base.events.Command
import ca.on.hojat.gamenews.shared.ui.base.events.Route

internal sealed class ImageViewerCommand : Command {
    data class ShareText(val text: String) : ImageViewerCommand()
}

sealed class ImageViewerRoute : Route {
    object Back : ImageViewerRoute()
}
