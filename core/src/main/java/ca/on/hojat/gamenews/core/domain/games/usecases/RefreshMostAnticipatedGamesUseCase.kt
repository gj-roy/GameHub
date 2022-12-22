package ca.on.hojat.gamenews.core.domain.games.usecases

import ca.on.hojat.gamenews.core.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.core.extensions.onEachSuccess
import ca.on.hojat.gamenews.core.domain.games.RefreshableGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.common.RefreshGamesUseCaseParams
import ca.on.hojat.gamenews.core.domain.games.common.throttling.GamesRefreshingThrottlerTools
import ca.on.hojat.gamenews.core.domain.games.datastores.GamesDataStores
import ca.on.hojat.gamenews.core.domain.entities.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

interface RefreshMostAnticipatedGamesUseCase : RefreshableGamesUseCase

@Singleton
internal class RefreshMostAnticipatedGamesUseCaseImpl @Inject constructor(
    private val gamesDataStores: GamesDataStores,
    private val throttlerTools: GamesRefreshingThrottlerTools,
    private val dispatcherProvider: DispatcherProvider,
) : RefreshMostAnticipatedGamesUseCase {

    override fun execute(params: RefreshGamesUseCaseParams): Flow<DomainResult<List<Game>>> {
        val throttlerKey =
            throttlerTools.keyProvider.provideMostAnticipatedGamesKey(params.pagination)

        return flow {
            if (throttlerTools.throttler.canRefreshGames(throttlerKey)) {
                emit(gamesDataStores.remote.getMostAnticipatedGames(params.pagination))
            }
        }
            .onEachSuccess { games ->
                gamesDataStores.local.saveGames(games)
                throttlerTools.throttler.updateGamesLastRefreshTime(throttlerKey)
            }
            .flowOn(dispatcherProvider.main)
    }
}
