package ca.on.hojat.gamenews.feature_info.presentation

import ca.on.hojat.gamenews.feature_info.domain.usecases.GetGameImageUrlsUseCase
import ca.on.hojat.gamenews.feature_info.domain.usecases.GetGameInfoUseCase
import ca.on.hojat.gamenews.feature_info.domain.usecases.likes.ToggleGameLikeStateUseCase
import javax.inject.Inject

internal class GameInfoUseCases @Inject constructor(
    val getGameInfoUseCase: GetGameInfoUseCase,
    val getGameImageUrlsUseCase: GetGameImageUrlsUseCase,
    val toggleGameLikeStateUseCase: ToggleGameLikeStateUseCase,
)
