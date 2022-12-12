package ca.on.hojat.gamenews.feature_discovery

import androidx.lifecycle.viewModelScope
import ca.on.hojat.gamenews.shared.core.ErrorMapper
import ca.on.hojat.gamenews.shared.core.Logger
import ca.on.hojat.gamenews.shared.core.providers.StringProvider
import ca.on.hojat.gamenews.shared.core.utils.onError
import ca.on.hojat.gamenews.shared.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.shared.domain.common.extensions.resultOrError
import ca.on.hojat.gamenews.shared.domain.games.common.ObserveGamesUseCaseParams
import ca.on.hojat.gamenews.shared.domain.games.common.RefreshGamesUseCaseParams
import ca.on.hojat.gamenews.shared.domain.games.entities.Game
import ca.on.hojat.gamenews.shared.ui.base.BaseViewModel
import ca.on.hojat.gamenews.shared.ui.base.events.common.GeneralCommand
import ca.on.hojat.gamenews.feature_discovery.mapping.GamesDiscoveryItemGameUiModelMapper
import ca.on.hojat.gamenews.feature_discovery.mapping.mapToUiModels
import ca.on.hojat.gamenews.feature_discovery.widgets.GamesDiscoveryItemGameUiModel
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
import javax.inject.Inject

@HiltViewModel
internal class GamesDiscoveryViewModel @Inject constructor(
    private val useCases: GamesDiscoveryUseCases,
    private val uiModelMapper: GamesDiscoveryItemGameUiModelMapper,
    private val dispatcherProvider: DispatcherProvider,
    private val stringProvider: StringProvider,
    private val errorMapper: ErrorMapper,
    private val logger: Logger
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
            GamesDiscoveryCategory.values().map { category ->
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
            flows = GamesDiscoveryCategory.values().map(::observeGames),
            transform = { it.toList() }
        )
            .map { games -> currentItems.toSuccessState(games) }
            .onError { logger.error(logTag, "Failed to observe games.", it) }
            .onStart { isObservingGames = true }
            .onCompletion { isObservingGames = false }
            .onEach { emittedItems -> _items.update { emittedItems } }
            .launchIn(viewModelScope)
    }

    private fun observeGames(category: GamesDiscoveryCategory): Flow<List<GamesDiscoveryItemGameUiModel>> {
        return useCases.getObservableUseCase(category.toKeyType())
            .execute(observeGamesUseCaseParams)
            .map(uiModelMapper::mapToUiModels)
            .flowOn(dispatcherProvider.computation)
    }

    private fun refreshGames() {
        if (isRefreshingGames) return

        combine(
            flows = GamesDiscoveryCategory.values().map(::refreshGames),
            transform = { it.toList() }
        )
            .map { currentItems }
            .onError {
                logger.error(logTag, "Failed to refresh games.", it)
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

    private fun refreshGames(category: GamesDiscoveryCategory): Flow<List<Game>> {
        return useCases.getRefreshableUseCase(category.toKeyType())
            .execute(refreshGamesUseCaseParams)
            .resultOrError()
    }

    fun onSearchButtonClicked() {
        route(GamesDiscoveryRoute.Search)
    }

    fun onCategoryMoreButtonClicked(category: String) {
        route(GamesDiscoveryRoute.Category(category))
    }

    fun onCategoryGameClicked(item: GamesDiscoveryItemGameUiModel) {
        route(GamesDiscoveryRoute.Info(gameId = item.id))
    }

    fun onRefreshRequested() {
        refreshGames()
    }
}
