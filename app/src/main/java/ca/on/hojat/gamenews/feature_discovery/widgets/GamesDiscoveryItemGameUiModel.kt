package ca.on.hojat.gamenews.feature_discovery.widgets

import androidx.compose.runtime.Immutable

@Immutable
internal data class GamesDiscoveryItemGameUiModel(
    val id: Int,
    val title: String,
    val coverUrl: String?,
)
