package ca.on.hojat.gamenews.feature_info.domain.usecases

import ca.on.hojat.gamenews.shared.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.shared.domain.common.entities.Pagination
import ca.on.hojat.gamenews.shared.extensions.onEachSuccess
import ca.on.hojat.gamenews.shared.domain.common.usecases.UseCase
import ca.on.hojat.gamenews.shared.domain.games.common.throttling.GamesRefreshingThrottlerTools
import ca.on.hojat.gamenews.shared.domain.games.datastores.GamesDataStores
import ca.on.hojat.gamenews.shared.domain.games.entities.Company
import ca.on.hojat.gamenews.shared.domain.games.entities.Game
import ca.on.hojat.gamenews.feature_info.domain.usecases.RefreshCompanyDevelopedGamesUseCase.Params
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
    private val gamesDataStores: GamesDataStores,
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
                    gamesDataStores.remote.getCompanyDevelopedGames(
                        params.company,
                        params.pagination
                    )
                )
            }
        }
            .onEachSuccess { games ->
                gamesDataStores.local.saveGames(games)
                throttlerTools.throttler.updateGamesLastRefreshTime(throttlerKey)
            }
            .flowOn(dispatcherProvider.main)
    }
}
