package ca.on.hojat.gamenews.feature_category.widgets

import androidx.compose.runtime.Immutable
import ca.on.hojat.gamenews.common_ui.widgets.FiniteUiState

@Immutable
internal data class GamesCategoryUiState(
    val isLoading: Boolean,
    val title: String,
    val games: List<GameCategoryUiModel>,
) {
    internal val finiteUiState: FiniteUiState
        get() = when {
            isInEmptyState -> FiniteUiState.Empty
            isInLoadingState -> FiniteUiState.Loading
            isInSuccessState -> FiniteUiState.Success
            else -> error("Unknown games category UI state.")
        }

    private val isInEmptyState: Boolean
        get() = (!isLoading && games.isEmpty())

    private val isInLoadingState: Boolean
        get() = (isLoading && games.isEmpty())

    private val isInSuccessState: Boolean
        get() = games.isNotEmpty()

    internal val isRefreshing: Boolean
        get() = (isLoading && games.isNotEmpty())

    internal fun enableLoading(): GamesCategoryUiState {
        return copy(isLoading = true)
    }

    internal fun disableLoading(): GamesCategoryUiState {
        return copy(isLoading = false)
    }

    internal fun toEmptyState(): GamesCategoryUiState {
        return copy(isLoading = false, games = emptyList())
    }

    internal fun toSuccessState(
        games: List<GameCategoryUiModel>
    ): GamesCategoryUiState {
        return copy(isLoading = false, games = games)
    }
}

@Immutable
internal data class GameCategoryUiModel(
    val id: Int,
    val title: String,
    val coverUrl: String?,
)
