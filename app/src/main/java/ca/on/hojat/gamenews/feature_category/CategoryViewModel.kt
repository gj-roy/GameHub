package ca.on.hojat.gamenews.feature_category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import ca.on.hojat.gamenews.common_ui.base.BaseViewModel
import ca.on.hojat.gamenews.common_ui.base.events.GeneralCommand
import ca.on.hojat.gamenews.common_ui.di.TransitionAnimationDuration
import ca.on.hojat.gamenews.core.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.games.common.ObserveGamesUseCaseParams
import ca.on.hojat.gamenews.core.domain.games.common.RefreshGamesUseCaseParams
import ca.on.hojat.gamenews.core.extensions.onError
import ca.on.hojat.gamenews.core.extensions.resultOrError
import ca.on.hojat.gamenews.core.mappers.ErrorMapper
import ca.on.hojat.gamenews.core.providers.StringProvider
import ca.on.hojat.gamenews.feature_category.di.GamesCategoryKey
import ca.on.hojat.gamenews.feature_category.widgets.CategoryItemModelMapper
import ca.on.hojat.gamenews.feature_category.widgets.CategoryUiModel
import ca.on.hojat.gamenews.feature_category.widgets.GamesCategoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val PARAM_CATEGORY = "category"

@HiltViewModel
internal class CategoryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    stringProvider: StringProvider,
    @TransitionAnimationDuration
    transitionAnimationDuration: Long,
    private val useCases: CategoryUseCases,
    private val uiModelMapper: CategoryItemModelMapper,
    private val dispatcherProvider: DispatcherProvider,
    private val errorMapper: ErrorMapper
) : BaseViewModel() {

    private var isObservingGames = false
    private var isRefreshingGames = false
    private var hasMoreGamesToLoad = false

    private var observeGamesUseCaseParams = ObserveGamesUseCaseParams()
    private var refreshGamesUseCaseParams = RefreshGamesUseCaseParams()

    private val categoryType: CategoryType
    private val gamesCategoryKeyType: GamesCategoryKey.Type

    private var gamesObservingJob: Job? = null
    private var gamesRefreshingJob: Job? = null

    private val _uiState = MutableStateFlow(createEmptyUiState())

    private val currentUiState: GamesCategoryUiState
        get() = _uiState.value

    val uiState: StateFlow<GamesCategoryUiState> = _uiState.asStateFlow()

    init {
        categoryType =
            CategoryType.valueOf(checkNotNull(savedStateHandle.get<String>(PARAM_CATEGORY)))
        gamesCategoryKeyType = categoryType.toKeyType()

        _uiState.update {
            it.copy(title = stringProvider.getString(categoryType.titleId))
        }

        observeGames(resultEmissionDelay = transitionAnimationDuration)
        refreshGames(resultEmissionDelay = transitionAnimationDuration)
    }

    private fun createEmptyUiState(): GamesCategoryUiState {
        return GamesCategoryUiState(
            isLoading = false,
            title = "",
            games = emptyList(),
        )
    }

    private fun observeGames(resultEmissionDelay: Long = 0L) {
        if (isObservingGames) return

        gamesObservingJob = useCases.getObservableGamesUseCase(gamesCategoryKeyType)
            .execute(observeGamesUseCaseParams)
            .map(uiModelMapper::mapToUiModels)
            .flowOn(dispatcherProvider.computation)
            .map { games -> currentUiState.toSuccessState(games) }
            .onError {
                Timber.e(it, "Failed to observe ${categoryType.name} games.")
                dispatchCommand(GeneralCommand.ShowLongToast(errorMapper.mapToMessage(it)))
                emit(currentUiState.toEmptyState())
            }
            .onStart {
                isObservingGames = true
                delay(resultEmissionDelay)
            }
            .onCompletion { isObservingGames = false }
            .onEach { emittedUiState ->
                configureNextLoad(emittedUiState)
                _uiState.update { emittedUiState }
            }
            .launchIn(viewModelScope)
    }

    private fun configureNextLoad(uiState: GamesCategoryUiState) {
        if (!uiState.hasLoadedNewGames()) return

        val paginationLimit = observeGamesUseCaseParams.pagination.limit
        val gameCount = uiState.games.size

        hasMoreGamesToLoad = (paginationLimit == gameCount)
    }

    private fun GamesCategoryUiState.hasLoadedNewGames(): Boolean {
        return (!isLoading && games.isNotEmpty())
    }

    private fun refreshGames(resultEmissionDelay: Long = 0L) {
        if (isRefreshingGames) return

        gamesRefreshingJob = useCases.getRefreshableGamesUseCase(gamesCategoryKeyType)
            .execute(refreshGamesUseCaseParams)
            .resultOrError()
            .map { currentUiState }
            .onError {
                Timber.e(it, "Failed to refresh ${categoryType.name} games.")
                dispatchCommand(GeneralCommand.ShowLongToast(errorMapper.mapToMessage(it)))
            }
            .onStart {
                isRefreshingGames = true
                emit(currentUiState.enableLoading())
                // Show loading state for some time since it can be too quick
                delay(resultEmissionDelay)
            }
            .onCompletion {
                isRefreshingGames = false
                // Delay disabling loading to avoid quick state changes like
                // empty, loading, empty, success
                delay(resultEmissionDelay)
                emit(currentUiState.disableLoading())
            }
            .onEach { emittedUiState -> _uiState.update { emittedUiState } }
            .launchIn(viewModelScope)
    }

    fun onToolbarLeftButtonClicked() {
        route(CategoryScreenRoute.Back)
    }

    fun onGameClicked(game: CategoryUiModel) {
        route(CategoryScreenRoute.Info(game.id))
    }

    fun onBottomReached() {
        loadMoreGames()
    }

    private fun loadMoreGames() {
        if (!hasMoreGamesToLoad) return

        viewModelScope.launch {
            fetchNextGamesBatch()
            observeNewGamesBatch()
        }
    }

    private suspend fun fetchNextGamesBatch() {
        refreshGamesUseCaseParams = refreshGamesUseCaseParams.copy(
            refreshGamesUseCaseParams.pagination.nextOffset()
        )

        gamesRefreshingJob?.cancelAndJoin()
        refreshGames()
        gamesRefreshingJob?.join()
    }

    private suspend fun observeNewGamesBatch() {
        observeGamesUseCaseParams = observeGamesUseCaseParams.copy(
            observeGamesUseCaseParams.pagination.nextLimit()
        )

        gamesObservingJob?.cancelAndJoin()
        observeGames()
    }
}
