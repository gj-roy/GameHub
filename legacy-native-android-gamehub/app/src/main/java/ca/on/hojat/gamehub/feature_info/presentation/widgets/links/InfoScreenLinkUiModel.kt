package ca.on.hojat.gamehub.feature_info.presentation.widgets.links

import androidx.compose.runtime.Immutable

@Immutable
internal data class InfoScreenLinkUiModel(
    val id: Int,
    val text: String,
    val iconId: Int,
    val url: String,
)
