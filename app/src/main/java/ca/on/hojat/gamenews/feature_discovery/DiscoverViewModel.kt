package ca.on.hojat.gamenews.feature_discovery

import androidx.lifecycle.viewModelScope
import ca.on.hojat.gamenews.common_ui.base.BaseViewModel
import ca.on.hojat.gamenews.common_ui.base.events.GeneralCommand
import ca.on.hojat.gamenews.core.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.entities.Game
import ca.on.hojat.gamenews.core.domain.games.common.ObserveGamesUseCaseParams
import ca.on.hojat.gamenews.core.domain.games.common.RefreshGamesUseCaseParams
import ca.on.hojat.gamenews.core.extensions.onError
import ca.on.hojat.gamenews.core.extensions.resultOrError
import ca.on.hojat.gamenews.core.mappers.ErrorMapper
import ca.on.hojat.gamenews.core.providers.StringProvider
import ca.on.hojat.gamenews.feature_discovery.mapping.DiscoverItemModelMapper
import ca.on.hojat.gamenews.feature_discovery.widgets.DiscoverScreen
import ca.on.hojat.gamenews.feature_discovery.widgets.DiscoverScreenItemData
import ca.on.hojat.gamenews.feature_discovery.widgets.GamesDiscoveryItemUiModel
import ca.on.hojat.gamenews.feature_discovery.widgets.hideProgressBar
import ca.on.hojat.gamenews.feature_discovery.widgets.showProgressBar
import ca.on.hojat.gamenews.feature_discovery.widgets.toSuccessState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject

/**
 * The only View Model that controls [DiscoverScreen].
 */
@HiltViewModel
internal class DiscoverViewModel @Inject constructor(
    private val useCases: DiscoverUseCases,
    private val uiModelMapper: DiscoverItemModelMapper,
    private val dispatcherProvider: DispatcherProvider,
    private val stringProvider: StringProvider,
    private val errorMapper: ErrorMapper
) : BaseViewModel() {

    private var isObservingGames = false
    private var isRefreshingGames = false

    private var observeGamesUseCaseParams = ObserveGamesUseCaseParams()
    private var refreshGamesUseCaseParams = RefreshGamesUseCaseParams()

    private val _items = MutableStateFlow<List<GamesDiscoveryItemUiModel>>(listOf())

    private val currentItems: List<GamesDiscoveryItemUiModel>
        get() = _items.value

    val items: StateFlow<List<GamesDiscoveryItemUiModel>> = _items.asStateFlow()

    init {
        initDiscoveryItemsData()
        observeGames()
        refreshGames()
    }

    private fun initDiscoveryItemsData() {
        _items.update {
            DiscoverType.values().map { category ->
                GamesDiscoveryItemUiModel(
                    id = category.id,
                    categoryName = category.name,
                    title = stringProvider.getString(category.titleId),
                    isProgressBarVisible = false,
                    games = emptyList(),
                )
            }
        }
    }

    private fun observeGames() {
        if (isObservingGames) return

        combine(
            flows = DiscoverType.values().map(::observeGames),
            transform = { it.toList() }
        )
            .map { games -> currentItems.toSuccessState(games) }
            .onError { Timber.e(it, "Failed to observe games.") }
            .onStart { isObservingGames = true }
            .onCompletion { isObservingGames = false }
            .onEach { emittedItems -> _items.update { emittedItems } }
            .launchIn(viewModelScope)
    }

    private fun observeGames(category: DiscoverType): Flow<List<DiscoverScreenItemData>> {
        return useCases.getObservableUseCase(category.toKeyType())
            .execute(observeGamesUseCaseParams)
            .map(uiModelMapper::mapToUiModels)
            .flowOn(dispatcherProvider.computation)
    }

    private fun refreshGames() {
        if (isRefreshingGames) return

        combine(
            flows = DiscoverType.values().map(::refreshGames),
            transform = { it.toList() }
        )
            .map { currentItems }
            .onError {
                Timber.e(it, "Failed to refresh games.")
                dispatchCommand(GeneralCommand.ShowLongToast(errorMapper.mapToMessage(it)))
            }
            .onStart {
                isRefreshingGames = true
                emit(currentItems.showProgressBar())
            }
            .onCompletion {
                isRefreshingGames = false
                emit(currentItems.hideProgressBar())
            }
            .onEach { emittedItems -> _items.update { emittedItems } }
            .launchIn(viewModelScope)
    }

    private fun refreshGames(category: DiscoverType): Flow<List<Game>> {
        return useCases.getRefreshableUseCase(category.toKeyType())
            .execute(refreshGamesUseCaseParams)
            .resultOrError()
    }

    fun onSearchButtonClicked() {
        route(DiscoverScreenRoute.Search)
    }

    fun onCategoryMoreButtonClicked(category: String) {
        route(DiscoverScreenRoute.Category(category))
    }

    fun onCategoryGameClicked(item: DiscoverScreenItemData) {
        route(DiscoverScreenRoute.Info(gameId = item.id))
    }

    fun onRefreshRequested() {
        refreshGames()
    }
}
