package ca.on.hojat.gamenews.feature_info.presentation

import ca.on.hojat.gamenews.feature_info.domain.usecases.game.GetGameImageUrlsUseCase
import ca.on.hojat.gamenews.feature_info.domain.usecases.game.GetGameInfoUseCase
import ca.on.hojat.gamenews.feature_info.domain.usecases.likes.ToggleLikeStateUseCase
import javax.inject.Inject

internal class InfoScreenUseCases @Inject constructor(
    val getGameInfoUseCase: GetGameInfoUseCase,
    val getGameImageUrlsUseCase: GetGameImageUrlsUseCase,
    val toggleLikeStateUseCase: ToggleLikeStateUseCase,
)
