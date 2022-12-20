package ca.on.hojat.gamenews.feature_info.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.core.extensions.onError
import ca.on.hojat.gamenews.core.extensions.resultOrError
import ca.on.hojat.gamenews.core.mappers.ErrorMapper
import ca.on.hojat.gamenews.core.providers.StringProvider
import ca.on.hojat.gamenews.feature_info.domain.entities.GameImageType
import ca.on.hojat.gamenews.feature_info.domain.usecases.GetGameImageUrlsUseCase
import ca.on.hojat.gamenews.feature_info.domain.usecases.GetGameInfoUseCase
import ca.on.hojat.gamenews.feature_info.domain.usecases.likes.ToggleGameLikeStateUseCase
import ca.on.hojat.gamenews.feature_info.presentation.widgets.companies.GameInfoCompanyUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.links.GameInfoLinkUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.main.GameInfoUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.main.GameInfoUiState
import ca.on.hojat.gamenews.feature_info.presentation.widgets.main.toEmptyState
import ca.on.hojat.gamenews.feature_info.presentation.widgets.main.toLoadingState
import ca.on.hojat.gamenews.feature_info.presentation.widgets.main.toSuccessState
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.GameInfoRelatedGameUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.videos.GameInfoVideoUiModel
import ca.on.hojat.gamenews.shared.core.Logger
import ca.on.hojat.gamenews.shared.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.shared.ui.base.BaseViewModel
import ca.on.hojat.gamenews.shared.ui.base.events.common.GeneralCommand
import ca.on.hojat.gamenews.shared.ui.di.qualifiers.TransitionAnimationDuration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val PARAM_GAME_ID = "game-id"

@HiltViewModel
@Suppress("LongParameterList")
internal class GameInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @TransitionAnimationDuration
    transitionAnimationDuration: Long,
    private val useCases: GameInfoUseCases,
    private val uiModelMapper: GameInfoUiModelMapper,
    private val dispatcherProvider: DispatcherProvider,
    private val stringProvider: StringProvider,
    private val errorMapper: ErrorMapper,
    private val logger: Logger
) : BaseViewModel() {

    private var isObservingGameData = false

    private val gameId = checkNotNull(savedStateHandle.get<Int>(PARAM_GAME_ID))

    private val _uiState = MutableStateFlow(GameInfoUiState(isLoading = false, game = null))

    private val currentUiState: GameInfoUiState
        get() = _uiState.value

    val uiState: StateFlow<GameInfoUiState> = _uiState.asStateFlow()

    init {
        observeGameInfo(resultEmissionDelay = transitionAnimationDuration)
    }

    private fun observeGameInfo(resultEmissionDelay: Long) {
        if (isObservingGameData) return

        viewModelScope.launch {
            observeGameInfoInternal(resultEmissionDelay)
        }
    }

    private suspend fun observeGameInfoInternal(resultEmissionDelay: Long) {
        useCases.getGameInfoUseCase.execute(GetGameInfoUseCase.Params(gameId))
            .map(uiModelMapper::mapToUiModel)
            .flowOn(dispatcherProvider.computation)
            .map { game -> currentUiState.toSuccessState(game) }
            .onError {
                logger.error(logTag, "Failed to load game info data.", it)
                dispatchCommand(GeneralCommand.ShowLongToast(errorMapper.mapToMessage(it)))
                emit(currentUiState.toEmptyState())
            }
            .onStart {
                isObservingGameData = true
                emit(currentUiState.toLoadingState())
                delay(resultEmissionDelay)
            }
            .onCompletion { isObservingGameData = false }
            .collect { emittedUiState -> _uiState.update { emittedUiState } }
    }

    fun onArtworkClicked(artworkIndex: Int) {
        navigateToImageViewer(
            title = stringProvider.getString(R.string.artwork),
            imageType = GameImageType.ARTWORK,
            initialPosition = artworkIndex,
        )
    }

    private fun navigateToImageViewer(
        title: String,
        imageType: GameImageType,
        initialPosition: Int = 0,
    ) {
        viewModelScope.launch {
            useCases.getGameImageUrlsUseCase.execute(
                GetGameImageUrlsUseCase.Params(
                    gameId = gameId,
                    imageType = imageType,
                )
            )
                .resultOrError()
                .onError {
                    logger.error(logTag, "Failed to get the image urls of type = $imageType.", it)
                    dispatchCommand(GeneralCommand.ShowLongToast(errorMapper.mapToMessage(it)))
                }
                .collect { imageUrls ->
                    route(GameInfoRoute.ImageViewer(title, initialPosition, imageUrls))
                }
        }
    }

    fun onBackButtonClicked() {
        route(GameInfoRoute.Back)
    }

    fun onCoverClicked() {
        navigateToImageViewer(
            title = stringProvider.getString(R.string.cover),
            imageType = GameImageType.COVER,
        )
    }

    fun onLikeButtonClicked() {
        viewModelScope.launch {
            useCases.toggleGameLikeStateUseCase
                .execute(ToggleGameLikeStateUseCase.Params(gameId))
        }
    }

    fun onVideoClicked(video: GameInfoVideoUiModel) {
        openUrl(video.videoUrl)
    }

    fun onScreenshotClicked(screenshotIndex: Int) {
        navigateToImageViewer(
            title = stringProvider.getString(R.string.screenshot),
            imageType = GameImageType.SCREENSHOT,
            initialPosition = screenshotIndex,
        )
    }

    fun onLinkClicked(link: GameInfoLinkUiModel) {
        openUrl(link.url)
    }

    fun onCompanyClicked(company: GameInfoCompanyUiModel) {
        openUrl(company.websiteUrl)
    }

    private fun openUrl(url: String) {
        dispatchCommand(GameInfoCommand.OpenUrl(url))
    }

    fun onRelatedGameClicked(game: GameInfoRelatedGameUiModel) {
        route(GameInfoRoute.Info(gameId = game.id))
    }
}
