package ca.on.hojat.gamenews.feature_info.presentation.widgets.links

import androidx.compose.runtime.Immutable

@Immutable
internal data class GameInfoLinkUiModel(
    val id: Int,
    val text: String,
    val iconId: Int,
    val url: String,
)
