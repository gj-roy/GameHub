package ca.on.hojat.gamenews.shared.ui

import androidx.compose.runtime.staticCompositionLocalOf
import ca.on.hojat.gamenews.core.providers.NetworkStateProvider
import ca.on.hojat.gamenews.core.sharers.TextSharer
import ca.on.hojat.gamenews.shared.core.urlopener.UrlOpener

val LocalUrlOpener = staticCompositionLocalOf<UrlOpener> {
    error("UrlOpener not provided.")
}

val LocalTextSharer = staticCompositionLocalOf<TextSharer> {
    error("TextSharer not provided.")
}

val LocalNetworkStateProvider = staticCompositionLocalOf<NetworkStateProvider> {
    error("NetworkStateProvider not provided.")
}
