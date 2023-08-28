package ca.on.hojat.gamehub.feature_info.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import ca.on.hojat.gamehub.R
import ca.on.hojat.gamehub.core.BaseViewModel
import ca.on.hojat.gamehub.core.events.GeneralCommand
import ca.on.hojat.gamehub.feature_image_viewer.ImageViewerViewModel
import ca.on.hojat.gamehub.presentation.di.TransitionAnimationDuration
import ca.on.hojat.gamehub.core.domain.common.DispatcherProvider
import ca.on.hojat.gamehub.core.extensions.onError
import ca.on.hojat.gamehub.core.extensions.resultOrError
import ca.on.hojat.gamehub.core.mappers.ErrorMapper
import ca.on.hojat.gamehub.core.providers.StringProvider
import ca.on.hojat.gamehub.feature_info.domain.entities.GameImageType
import ca.on.hojat.gamehub.feature_info.domain.usecases.game.GetGameImageUrlsUseCase
import ca.on.hojat.gamehub.feature_info.domain.usecases.game.GetGameInfoUseCase
import ca.on.hojat.gamehub.feature_info.domain.usecases.likes.ToggleLikeStateUseCase
import ca.on.hojat.gamehub.feature_info.presentation.widgets.companies.InfoScreenCompanyUiModel
import ca.on.hojat.gamehub.feature_info.presentation.widgets.links.InfoScreenLinkUiModel
import ca.on.hojat.gamehub.feature_info.presentation.widgets.main.InfoScreenUiState
import ca.on.hojat.gamehub.feature_info.presentation.widgets.main.InfoScreenUiModelMapper
import ca.on.hojat.gamehub.feature_info.presentation.widgets.relatedgames.RelatedGameUiModel
import ca.on.hojat.gamehub.feature_info.presentation.widgets.videos.InfoScreenVideoUiModel
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
import timber.log.Timber
import javax.inject.Inject

private const val PARAM_GAME_ID = "game-id"

@HiltViewModel
@Suppress("LongParameterList")
internal class InfoScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @TransitionAnimationDuration
    transitionAnimationDuration: Long,
    private val useCases: InfoScreenUseCases,
    private val uiModelMapper: InfoScreenUiModelMapper,
    private val dispatcherProvider: DispatcherProvider,
    private val stringProvider: StringProvider,
    private val errorMapper: ErrorMapper
) : BaseViewModel() {

    private var isObservingGameData = false

    private val gameId = checkNotNull(savedStateHandle.get<Int>(PARAM_GAME_ID))

    private val _uiState = MutableStateFlow(InfoScreenUiState(isLoading = false, game = null))

    private val currentUiState: InfoScreenUiState
        get() = _uiState.value

    val uiState: StateFlow<InfoScreenUiState> = _uiState.asStateFlow()

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
                Timber.e(it, "Failed to load game info data.")
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
            gameName = currentUiState.game?.headerModel?.title ?: "",
            title = stringProvider.getString(R.string.artwork),
            imageType = GameImageType.ARTWORK,
            initialPosition = artworkIndex,
        )
    }

    /**
     * The data that goes from [InfoScreenViewModel] to [ImageViewerViewModel].
     */
    private fun navigateToImageViewer(
        gameName: String,
        title: String,
        imageType: GameImageType,
        initialPosition: Int = 0,
    ) {
        viewModelScope.launch {
            useCases.getGameImageUrlsUseCase.execute(
                GetGameImageUrlsUseCase.Params(
                    gameId = gameId,
                    gameImageType = imageType,
                )
            )
                .resultOrError()
                .onError {
                    Timber.e(it, "Failed to get the image urls of type = $imageType.")
                    dispatchCommand(GeneralCommand.ShowLongToast(errorMapper.mapToMessage(it)))
                }
                .collect { imageUrls ->
                    route(
                        InfoScreenRoute.ImageViewer(
                            gameName = gameName,
                            title = title,
                            initialPosition = initialPosition,
                            imageUrls = imageUrls
                        )
                    )
                }
        }
    }

    fun onBackButtonClicked() {
        route(InfoScreenRoute.Back)
    }

    fun onCoverClicked() {
        navigateToImageViewer(
            gameName = currentUiState.game?.headerModel?.title ?: "",
            title = stringProvider.getString(R.string.cover),
            imageType = GameImageType.COVER,
        )
    }

    fun onLikeButtonClicked() {
        viewModelScope.launch {
            useCases.toggleLikeStateUseCase
                .execute(ToggleLikeStateUseCase.Params(gameId))
        }
    }

    fun onVideoClicked(video: InfoScreenVideoUiModel) {
        openUrl(video.videoUrl)
    }

    fun onScreenshotClicked(screenshotIndex: Int) {
        navigateToImageViewer(
            gameName = currentUiState.game?.headerModel?.title ?: "",
            title = stringProvider.getString(R.string.screenshot),
            imageType = GameImageType.SCREENSHOT,
            initialPosition = screenshotIndex,
        )
    }

    fun onLinkClicked(link: InfoScreenLinkUiModel) {
        openUrl(link.url)
    }

    fun onCompanyClicked(company: InfoScreenCompanyUiModel) {
        openUrl(company.websiteUrl)
    }

    private fun openUrl(url: String) {
        dispatchCommand(InfoScreenCommand.OpenUrl(url))
    }

    fun onRelatedGameClicked(game: RelatedGameUiModel) {
        route(InfoScreenRoute.InfoScreen(id = game.id))
    }
}
