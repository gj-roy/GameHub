package ca.on.hojat.gamenews.feature_likes.presentation

import androidx.lifecycle.viewModelScope
import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.core.mappers.ErrorMapper
import ca.on.hojat.gamenews.core.providers.StringProvider
import ca.on.hojat.gamenews.feature_likes.domain.ObserveLikedGamesUseCase
import ca.on.hojat.gamenews.shared.core.Logger
import ca.on.hojat.gamenews.shared.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.shared.domain.common.entities.hasDefaultLimit
import ca.on.hojat.gamenews.shared.domain.common.entities.nextLimit
import ca.on.hojat.gamenews.shared.domain.games.common.ObserveGamesUseCaseParams
import ca.on.hojat.gamenews.shared.extensions.onError
import ca.on.hojat.gamenews.shared.ui.base.BaseViewModel
import ca.on.hojat.gamenews.shared.ui.base.events.common.GeneralCommand
import ca.on.hojat.gamenews.shared.ui.widgets.games.GameUiModel
import ca.on.hojat.gamenews.shared.ui.widgets.games.GameUiModelMapper
import ca.on.hojat.gamenews.shared.ui.widgets.games.GamesUiState
import ca.on.hojat.gamenews.shared.ui.widgets.games.mapToUiModels
import ca.on.hojat.gamenews.shared.ui.widgets.games.toEmptyState
import ca.on.hojat.gamenews.shared.ui.widgets.games.toLoadingState
import ca.on.hojat.gamenews.shared.ui.widgets.games.toSuccessState
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
import javax.inject.Inject

private const val SUBSEQUENT_EMISSION_DELAY = 500L

@HiltViewModel
internal class LikedGamesViewModel @Inject constructor(
    private val observeLikedGamesUseCase: ObserveLikedGamesUseCase,
    private val uiModelMapper: GameUiModelMapper,
    private val dispatcherProvider: DispatcherProvider,
    private val stringProvider: StringProvider,
    private val errorMapper: ErrorMapper,
    private val logger: Logger
) : BaseViewModel() {

    private var isObservingGames = false
    private var hasMoreGamesToLoad = false

    private var observeGamesUseCaseParams = ObserveGamesUseCaseParams()

    private var gamesObservingJob: Job? = null

    private val _uiState = MutableStateFlow(createDefaultUiState())

    private val currentUiState: GamesUiState
        get() = _uiState.value

    val uiState: StateFlow<GamesUiState> = _uiState.asStateFlow()

    init {
        observeGames()
    }

    private fun createDefaultUiState(): GamesUiState {
        return GamesUiState(
            isLoading = false,
            infoIconId = R.drawable.account_heart_outline,
            infoTitle = stringProvider.getString(R.string.liked_games_info_title),
            games = emptyList(),
        )
    }

    private fun observeGames() {
        if (isObservingGames) return

        gamesObservingJob = observeLikedGamesUseCase.execute(observeGamesUseCaseParams)
            .map(uiModelMapper::mapToUiModels)
            .flowOn(dispatcherProvider.computation)
            .map { games -> currentUiState.toSuccessState(games) }
            .onError {
                logger.error(logTag, "Failed to load liked games.", it)
                dispatchCommand(GeneralCommand.ShowLongToast(errorMapper.mapToMessage(it)))
                emit(currentUiState.toEmptyState())
            }
            .onStart {
                isObservingGames = true
                emit(currentUiState.toLoadingState())

                // Delaying to give a sense of "loading" since progress indicators
                // do not have the time to fully show themselves
                if (isSubsequentEmission()) delay(SUBSEQUENT_EMISSION_DELAY)
            }
            .onCompletion { isObservingGames = false }
            .onEach { emittedUiState ->
                configureNextLoad(emittedUiState)
                _uiState.update { emittedUiState }
            }
            .launchIn(viewModelScope)
    }

    private fun isSubsequentEmission(): Boolean {
        return !observeGamesUseCaseParams.pagination.hasDefaultLimit()
    }

    private fun configureNextLoad(uiState: GamesUiState) {
        if (!uiState.hasLoadedNewGames()) return

        val paginationLimit = observeGamesUseCaseParams.pagination.limit
        val itemCount = uiState.games.size

        hasMoreGamesToLoad = (paginationLimit == itemCount)
    }

    private fun GamesUiState.hasLoadedNewGames(): Boolean {
        return (!isLoading && games.isNotEmpty())
    }

    fun onSearchButtonClicked() {
        route(LikedGamesRoute.Search)
    }

    fun onGameClicked(game: GameUiModel) {
        route(LikedGamesRoute.Info(game.id))
    }

    fun onBottomReached() {
        observeNewGamesBatch()
    }

    private fun observeNewGamesBatch() {
        if (!hasMoreGamesToLoad) return

        observeGamesUseCaseParams = observeGamesUseCaseParams.copy(
            observeGamesUseCaseParams.pagination.nextLimit()
        )

        viewModelScope.launch {
            gamesObservingJob?.cancelAndJoin()
            observeGames()
        }
    }
}
