package com.paulrybitskyi.gamedge.feature.search.presentation

import androidx.compose.runtime.Immutable
import ca.on.hojat.gamenews.shared.ui.widgets.games.GameUiModel
import ca.on.hojat.gamenews.shared.ui.widgets.games.GamesUiState

@Immutable
internal data class GamesSearchUiState(
    val queryText: String,
    val gamesUiState: GamesUiState,
)

internal fun GamesUiState.toLoadingState(games: List<GameUiModel>): GamesUiState {
    return copy(
        isLoading = true,
        games = games,
    )
}

internal fun GamesUiState.toSuccessState(
    infoTitle: String,
    games: List<GameUiModel>,
): GamesUiState {
    return copy(
        isLoading = false,
        infoTitle = infoTitle,
        games = games
    )
}
