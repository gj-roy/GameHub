package ca.on.hojat.gamenews.presentation.widgets.categorypreview

import androidx.compose.runtime.Immutable

@Immutable
data class GamesCategoryPreviewItemUiModel(
    val id: Int,
    val title: String,
    val coverUrl: String?,
)
