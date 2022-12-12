package ca.on.hojat.gamenews.feature_info.presentation.widgets.videos

import androidx.compose.runtime.Immutable

@Immutable
internal data class GameInfoVideoUiModel(
    val id: String,
    val thumbnailUrl: String,
    val videoUrl: String,
    val title: String,
)
