package ca.on.hojat.gamenews.feature_info

import ca.on.hojat.gamenews.core.domain.entities.Company
import ca.on.hojat.gamenews.core.domain.entities.InvolvedCompany
import ca.on.hojat.gamenews.core.common_testing.domain.DOMAIN_GAME
import ca.on.hojat.gamenews.core.common_testing.domain.DOMAIN_GAMES
import ca.on.hojat.gamenews.core.common_testing.domain.PAGINATION
import ca.on.hojat.gamenews.feature_info.domain.usecases.game.RefreshSimilarGamesUseCase
import ca.on.hojat.gamenews.feature_info.domain.entities.GameInfo
import ca.on.hojat.gamenews.feature_info.domain.usecases.game.GetCompanyDevelopedGamesUseCase
import ca.on.hojat.gamenews.feature_info.domain.usecases.game.GetGameUseCase
import ca.on.hojat.gamenews.feature_info.domain.usecases.game.GetSimilarGamesUseCase
import ca.on.hojat.gamenews.feature_info.domain.usecases.game.RefreshCompanyDevelopedGamesUseCase
import ca.on.hojat.gamenews.feature_info.domain.usecases.likes.ObserveLikeStateUseCase
import ca.on.hojat.gamenews.feature_info.domain.usecases.likes.ToggleLikeStateUseCase

private val COMPANY = Company(
    id = 1,
    name = "name",
    websiteUrl = "url",
    logo = null,
    developedGames = listOf(1, 2, 3),
)

val GAME_INFO = GameInfo(
    game = DOMAIN_GAME,
    isGameLiked = true,
    companyGames = DOMAIN_GAMES,
    similarGames = DOMAIN_GAMES,
)
val INVOLVED_COMPANY = InvolvedCompany(
    company = COMPANY,
    isDeveloper = false,
    isPublisher = false,
    isPorter = false,
    isSupporting = false,
)

internal val OBSERVE_GAME_LIKE_STATE_USE_CASE_PARAMS =
    ObserveLikeStateUseCase.Params(id = 10)
internal val TOGGLE_GAME_LIKE_STATE_USE_CASE_PARAMS = ToggleLikeStateUseCase.Params(id = 10)
internal val GET_GAME_USE_CASE_PARAMS = GetGameUseCase.Params(gameId = 10)
internal val GET_COMPANY_DEVELOPED_GAMES_USE_CASE_PARAMS = GetCompanyDevelopedGamesUseCase.Params(
    COMPANY,
    PAGINATION,
)
internal val REFRESH_COMPANY_DEVELOPED_GAMES_USE_CASE_PARAMS =
    RefreshCompanyDevelopedGamesUseCase.Params(
        COMPANY,
        PAGINATION,
    )
internal val GET_SIMILAR_GAMES_USE_CASE_PARAMS =
    GetSimilarGamesUseCase.Params(DOMAIN_GAME, PAGINATION)
internal val REFRESH_SIMILAR_GAMES_USE_CASE_PARAMS =
    RefreshSimilarGamesUseCase.Params(DOMAIN_GAME, PAGINATION)
