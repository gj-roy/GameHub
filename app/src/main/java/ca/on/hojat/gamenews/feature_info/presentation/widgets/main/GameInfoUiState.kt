package ca.on.hojat.gamenews.feature_info.presentation.widgets.main

import androidx.compose.runtime.Immutable
import ca.on.hojat.gamenews.common_ui.widgets.FiniteUiState

@Immutable
internal data class GameInfoUiState(
    val isLoading: Boolean,
    val game: GameInfoUiModel?,
)

internal val GameInfoUiState.finiteUiState: FiniteUiState
    get() = when {
        isInEmptyState -> FiniteUiState.Empty
        isInLoadingState -> FiniteUiState.Loading
        isInSuccessState -> FiniteUiState.Success
        else -> error("Unknown game info UI state.")
    }

private val GameInfoUiState.isInEmptyState: Boolean
    get() = (!isLoading && (game == null))

private val GameInfoUiState.isInLoadingState: Boolean
    get() = (isLoading && (game == null))

private val GameInfoUiState.isInSuccessState: Boolean
    get() = (game != null)

internal fun GameInfoUiState.toEmptyState(): GameInfoUiState {
    return copy(isLoading = false, game = null)
}

internal fun GameInfoUiState.toLoadingState(): GameInfoUiState {
    return copy(isLoading = true)
}

internal fun GameInfoUiState.toSuccessState(game: GameInfoUiModel): GameInfoUiState {
    return copy(isLoading = false, game = game)
}
