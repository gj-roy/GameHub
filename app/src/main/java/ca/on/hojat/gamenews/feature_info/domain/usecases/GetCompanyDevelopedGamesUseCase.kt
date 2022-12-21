package ca.on.hojat.gamenews.feature_info.domain.usecases

import ca.on.hojat.gamenews.shared.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.shared.domain.common.usecases.UseCase
import ca.on.hojat.gamenews.shared.domain.games.datastores.GamesLocalDataStore
import ca.on.hojat.gamenews.core.domain.entities.Company
import ca.on.hojat.gamenews.core.domain.entities.Game
import ca.on.hojat.gamenews.core.domain.entities.Pagination
import com.github.michaelbull.result.Ok
import ca.on.hojat.gamenews.feature_info.domain.usecases.GetCompanyDevelopedGamesUseCase.Params
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import javax.inject.Inject
import javax.inject.Singleton

internal interface GetCompanyDevelopedGamesUseCase :
    UseCase<Params, Flow<DomainResult<List<Game>>>> {

    data class Params(
        val company: Company,
        val pagination: Pagination
    )
}

@Singleton
@BindType
internal class GetCompanyDevelopedGamesUseCaseImpl @Inject constructor(
    private val refreshCompanyDevelopedGamesUseCase: RefreshCompanyDevelopedGamesUseCase,
    private val gamesLocalDataStore: GamesLocalDataStore,
    private val dispatcherProvider: DispatcherProvider,
) : GetCompanyDevelopedGamesUseCase {

    override suspend fun execute(params: Params): Flow<DomainResult<List<Game>>> {
        return refreshCompanyDevelopedGamesUseCase
            .execute(RefreshCompanyDevelopedGamesUseCase.Params(params.company, params.pagination))
            .onEmpty {
                val localCompanyDevelopedGamesFlow = flow {
                    emit(
                        gamesLocalDataStore.getCompanyDevelopedGames(
                            params.company,
                            params.pagination
                        )
                    )
                }
                    .map<List<Game>, DomainResult<List<Game>>>(::Ok)

                emitAll(localCompanyDevelopedGamesFlow)
            }
            .flowOn(dispatcherProvider.main)
    }
}
