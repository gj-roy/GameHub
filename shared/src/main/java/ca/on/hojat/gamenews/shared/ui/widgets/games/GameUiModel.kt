package ca.on.hojat.gamenews.shared.ui.widgets.games

import androidx.compose.runtime.Immutable

@Immutable
data class GameUiModel(
    val id: Int,
    val coverImageUrl: String?,
    val name: String,
    val releaseDate: String,
    val developerName: String?,
    val description: String?
)
