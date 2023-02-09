package ca.on.hojat.gamenews.core.domain.games.usecases

import ca.on.hojat.gamenews.core.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.core.extensions.onEachSuccess
import ca.on.hojat.gamenews.core.domain.games.RefreshableGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.common.RefreshUseCaseParams
import ca.on.hojat.gamenews.core.domain.games.common.throttling.GamesRefreshingThrottlerTools
import ca.on.hojat.gamenews.core.domain.games.repository.GamesRepository
import ca.on.hojat.gamenews.core.domain.entities.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

interface RefreshPopularGamesUseCase : RefreshableGamesUseCase

@Singleton
class RefreshPopularGamesUseCaseImpl @Inject constructor(
    private val gamesRepository: GamesRepository,
    private val throttlerTools: GamesRefreshingThrottlerTools,
    private val dispatcherProvider: DispatcherProvider,
) : RefreshPopularGamesUseCase {

    override fun execute(params: RefreshUseCaseParams): Flow<DomainResult<List<Game>>> {
        val throttlerKey = throttlerTools.keyProvider.providePopularGamesKey(params.pagination)

        return flow {
            if (throttlerTools.throttler.canRefreshGames(throttlerKey)) {
                emit(gamesRepository.remote.getPopularGames(params.pagination))
            }
        }
            .onEachSuccess { games ->
                gamesRepository.local.saveGames(games)
                throttlerTools.throttler.updateGamesLastRefreshTime(throttlerKey)
            }
            .flowOn(dispatcherProvider.main)
    }
}
