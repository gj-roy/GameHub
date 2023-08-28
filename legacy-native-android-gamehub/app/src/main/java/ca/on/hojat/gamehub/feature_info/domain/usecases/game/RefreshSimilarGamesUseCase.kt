package ca.on.hojat.gamehub.feature_info.domain.usecases.game

import ca.on.hojat.gamehub.core.domain.common.DispatcherProvider
import ca.on.hojat.gamehub.core.domain.DomainResult
import ca.on.hojat.gamehub.core.extensions.onEachSuccess
import ca.on.hojat.gamehub.core.domain.entities.Pagination
import ca.on.hojat.gamehub.core.domain.common.usecases.UseCase
import ca.on.hojat.gamehub.core.domain.games.common.throttling.GamesRefreshingThrottlerTools
import ca.on.hojat.gamehub.core.domain.games.repository.GamesRepository
import ca.on.hojat.gamehub.core.domain.entities.Game
import ca.on.hojat.gamehub.feature_info.domain.usecases.game.RefreshSimilarGamesUseCase.Params
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

internal interface RefreshSimilarGamesUseCase : UseCase<Params, Flow<DomainResult<List<Game>>>> {

    data class Params(
        val game: Game,
        val pagination: Pagination
    )
}

@Singleton
@BindType
internal class RefreshSimilarGamesUseCaseImpl @Inject constructor(
    private val gamesRepository: GamesRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val throttlerTools: GamesRefreshingThrottlerTools,
) : RefreshSimilarGamesUseCase {

    override suspend fun execute(params: Params): Flow<DomainResult<List<Game>>> {
        val throttlerKey = withContext(dispatcherProvider.computation) {
            throttlerTools.keyProvider.provideSimilarGamesKey(
                params.game,
                params.pagination
            )
        }

        return flow {
            if (throttlerTools.throttler.canRefreshSimilarGames(throttlerKey)) {
                emit(gamesRepository.remote.getSimilarGames(params.game, params.pagination))
            }
        }
            .onEachSuccess { games ->
                gamesRepository.local.saveGames(games)
                throttlerTools.throttler.updateGamesLastRefreshTime(throttlerKey)
            }
            .flowOn(dispatcherProvider.main)
    }
}
