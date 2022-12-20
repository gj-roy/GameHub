package ca.on.hojat.gamenews.feature_info.domain.usecases

import ca.on.hojat.gamenews.core.extensions.combine
import ca.on.hojat.gamenews.core.extensions.resultOrError
import ca.on.hojat.gamenews.shared.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.shared.domain.common.entities.Pagination
import ca.on.hojat.gamenews.shared.domain.common.usecases.UseCase
import ca.on.hojat.gamenews.shared.domain.games.entities.Company
import ca.on.hojat.gamenews.shared.domain.games.entities.Game
import ca.on.hojat.gamenews.feature_info.domain.entities.GameInfo
import ca.on.hojat.gamenews.feature_info.domain.usecases.likes.ObserveGameLikeStateUseCase
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

internal interface GetGameInfoUseCase : UseCase<GetGameInfoUseCase.Params, Flow<GameInfo>> {

    data class Params(val gameId: Int)
}

@Singleton
@BindType
internal class GetGameInfoUseCaseImpl @Inject constructor(
    private val getGameUseCase: GetGameUseCase,
    private val observeGameLikeStateUseCase: ObserveGameLikeStateUseCase,
    private val getCompanyDevelopedGamesUseCase: GetCompanyDevelopedGamesUseCase,
    private val getSimilarGamesUseCase: GetSimilarGamesUseCase,
    private val dispatcherProvider: DispatcherProvider,
) : GetGameInfoUseCase {

    private companion object {
        private val RELATED_GAMES_PAGINATION = Pagination()
    }

    override suspend fun execute(params: GetGameInfoUseCase.Params): Flow<GameInfo> {
        return getGameUseCase.execute(GetGameUseCase.Params(params.gameId))
            .resultOrError()
            .flatMapConcat { game ->
                combine(
                    flowOf(game),
                    observeGameLikeState(params.gameId),
                    getCompanyGames(game),
                    getSimilarGames(game),
                )
            }
            .map { (game, isGameLiked, companyGames, similarGames) ->
                GameInfo(
                    game = game,
                    isGameLiked = isGameLiked,
                    companyGames = companyGames,
                    similarGames = similarGames,
                )
            }
            .flowOn(dispatcherProvider.main)
    }

    private fun observeGameLikeState(gameId: Int): Flow<Boolean> {
        return observeGameLikeStateUseCase.execute(
            ObserveGameLikeStateUseCase.Params(gameId)
        )
    }

    private suspend fun getCompanyGames(game: Game): Flow<List<Game>> {
        val company = game.developerCompany
            ?.takeIf(Company::hasDevelopedGames)
            ?: return flowOf(emptyList())

        return getCompanyDevelopedGamesUseCase.execute(
            GetCompanyDevelopedGamesUseCase.Params(company, RELATED_GAMES_PAGINATION),
        )
            .resultOrError()
    }

    private suspend fun getSimilarGames(game: Game): Flow<List<Game>> {
        if (!game.hasSimilarGames) return flowOf(emptyList())

        return getSimilarGamesUseCase.execute(
            GetSimilarGamesUseCase.Params(game, RELATED_GAMES_PAGINATION),
        )
            .resultOrError()
    }
}
