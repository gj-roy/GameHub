package ca.on.hojat.gamehub.feature_info.presentation.widgets.relatedgames

import androidx.compose.runtime.Immutable

@Immutable
internal data class RelatedGamesUiModel(
    val type: RelatedGamesType,
    val title: String,
    val items: List<RelatedGameUiModel>,
) {

    val hasItems: Boolean
        get() = items.isNotEmpty()
}
