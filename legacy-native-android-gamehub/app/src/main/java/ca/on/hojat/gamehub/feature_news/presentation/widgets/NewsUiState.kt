package ca.on.hojat.gamehub.feature_news.presentation.widgets

import androidx.compose.runtime.Immutable
import ca.on.hojat.gamehub.presentation.widgets.FiniteUiState

@Immutable
internal data class NewsUiState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val news: List<NewsItemUiModel> = emptyList(),
) {

    internal val finiteUiState: FiniteUiState
        get() = when {
            isInEmptyState -> FiniteUiState.EMPTY
            isLoading -> FiniteUiState.LOADING
            isInSuccessState -> FiniteUiState.SUCCESS
            else -> error("Unknown gaming news UI state.")
        }

    private val isInEmptyState: Boolean
        get() = (!isLoading && news.isEmpty())

    private val isInSuccessState: Boolean
        get() = news.isNotEmpty()

    internal fun toEmptyState(): NewsUiState {
        return copy(isLoading = false, news = emptyList())
    }

    internal fun toLoadingState(): NewsUiState {
        return copy(isLoading = true)
    }

    internal fun toSuccessState(
        news: List<NewsItemUiModel>
    ): NewsUiState {
        return copy(isLoading = false, news = news)
    }

    internal fun enableRefreshing(): NewsUiState {
        return copy(isRefreshing = true)
    }

    internal fun disableRefreshing(): NewsUiState {
        return copy(isRefreshing = false)
    }

}
