package ca.on.hojat.gamenews.feature_image_viewer

import ca.on.hojat.gamenews.common_ui.base.events.Command
import ca.on.hojat.gamenews.common_ui.base.events.Route

internal sealed class ImageViewerCommand : Command {
    /**
     * The command that ViewModel sends when user
     * wants to share a text with other users.
     */
    data class ShareText(val text: String) : ImageViewerCommand()

    /**
     * The command for downloading a file into device storage.
     */
    data class DownloadFile(val url: String) : ImageViewerCommand()
}

sealed class ImageViewerRoute : Route {
    object Back : ImageViewerRoute()
}
