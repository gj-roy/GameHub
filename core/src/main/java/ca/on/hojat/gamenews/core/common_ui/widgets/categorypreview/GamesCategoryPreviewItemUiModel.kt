package ca.on.hojat.gamenews.core.common_ui.widgets.categorypreview

import androidx.compose.runtime.Immutable

@Immutable
data class GamesCategoryPreviewItemUiModel(
    val id: Int,
    val title: String,
    val coverUrl: String?,
)
