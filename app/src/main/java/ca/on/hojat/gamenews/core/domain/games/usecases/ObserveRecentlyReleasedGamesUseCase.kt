package ca.on.hojat.gamenews.core.domain.games.usecases

import ca.on.hojat.gamenews.core.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.games.ObservableGamesUseCase
import ca.on.hojat.gamenews.core.domain.games.common.ObserveUseCaseParams
import ca.on.hojat.gamenews.core.domain.games.repository.GamesLocalDataSource
import ca.on.hojat.gamenews.core.domain.entities.Game
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

interface ObserveRecentlyReleasedGamesUseCase : ObservableGamesUseCase

@Singleton
class ObserveRecentlyReleasedGamesUseCaseImpl @Inject constructor(
    private val gamesLocalDataSource: GamesLocalDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : ObserveRecentlyReleasedGamesUseCase {

    override fun execute(params: ObserveUseCaseParams): Flow<List<Game>> {
        return gamesLocalDataSource.observeRecentlyReleasedGames(params.pagination)
            .flowOn(dispatcherProvider.main)
    }
}
