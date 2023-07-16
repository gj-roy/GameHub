package ca.on.hojat.gamenews.feature_info.domain.usecases.game

import ca.on.hojat.gamenews.core.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.DomainResult
import ca.on.hojat.gamenews.core.domain.entities.Error
import ca.on.hojat.gamenews.core.domain.common.usecases.UseCase
import ca.on.hojat.gamenews.core.domain.games.repository.GamesLocalDataSource
import ca.on.hojat.gamenews.core.domain.entities.Game
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import ca.on.hojat.gamenews.feature_info.domain.usecases.game.GetGameUseCase.Params
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEmpty
import javax.inject.Inject
import javax.inject.Singleton

internal interface GetGameUseCase : UseCase<Params, Flow<DomainResult<Game>>> {

    data class Params(val gameId: Int)
}

@Singleton
@BindType
internal class GetGameUseCaseImpl @Inject constructor(
    private val gamesLocalDataSource: GamesLocalDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : GetGameUseCase {

    override suspend fun execute(params: Params): Flow<DomainResult<Game>> {
        return flow { gamesLocalDataSource.getGame(params.gameId)?.let { emit(it) } }
            .map<Game, DomainResult<Game>>(::Ok)
            .onEmpty { emit(Err(Error.NotFound("Could not find the game with ID = ${params.gameId}"))) }
            .flowOn(dispatcherProvider.main)
    }
}
