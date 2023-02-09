package ca.on.hojat.gamenews.feature_info.domain.usecases.game

import ca.on.hojat.gamenews.core.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.core.extensions.onEachSuccess
import ca.on.hojat.gamenews.core.domain.entities.Pagination
import ca.on.hojat.gamenews.core.domain.common.usecases.UseCase
import ca.on.hojat.gamenews.core.domain.games.common.throttling.GamesRefreshingThrottlerTools
import ca.on.hojat.gamenews.core.domain.games.repository.GamesRepository
import ca.on.hojat.gamenews.core.domain.entities.Company
import ca.on.hojat.gamenews.core.domain.entities.Game
import ca.on.hojat.gamenews.feature_info.domain.usecases.game.RefreshCompanyDevelopedGamesUseCase.Params
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

internal interface RefreshCompanyDevelopedGamesUseCase :
    UseCase<Params, Flow<DomainResult<List<Game>>>> {

    data class Params(
        val company: Company,
        val pagination: Pagination
    )
}

@Singleton
@BindType
internal class RefreshCompanyDevelopedGamesUseCaseImpl @Inject constructor(
    private val gamesRepository: GamesRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val throttlerTools: GamesRefreshingThrottlerTools,
) : RefreshCompanyDevelopedGamesUseCase {

    override suspend fun execute(params: Params): Flow<DomainResult<List<Game>>> {
        val throttlerKey = withContext(dispatcherProvider.computation) {
            throttlerTools.keyProvider.provideCompanyDevelopedGamesKey(
                params.company,
                params.pagination
            )
        }

        return flow {
            if (throttlerTools.throttler.canRefreshCompanyDevelopedGames(throttlerKey)) {
                emit(
                    gamesRepository.remote.getCompanyDevelopedGames(
                        params.company,
                        params.pagination
                    )
                )
            }
        }
            .onEachSuccess { games ->
                gamesRepository.local.saveGames(games)
                throttlerTools.throttler.updateGamesLastRefreshTime(throttlerKey)
            }
            .flowOn(dispatcherProvider.main)
    }
}
