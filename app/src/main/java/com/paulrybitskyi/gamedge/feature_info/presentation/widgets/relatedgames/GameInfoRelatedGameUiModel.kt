package com.paulrybitskyi.gamedge.feature_info.presentation.widgets.relatedgames

import androidx.compose.runtime.Immutable

@Immutable
internal data class GameInfoRelatedGameUiModel(
    val id: Int,
    val title: String,
    val coverUrl: String?,
)
