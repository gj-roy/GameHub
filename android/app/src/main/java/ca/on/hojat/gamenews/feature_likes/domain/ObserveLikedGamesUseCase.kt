package ca.on.hojat.gamenews.feature_likes.domain

import ca.on.hojat.gamenews.core.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.core.domain.games.common.ObserveUseCaseParams
import ca.on.hojat.gamenews.core.domain.games.repository.LikedGamesLocalDataSource
import ca.on.hojat.gamenews.core.domain.entities.Game
import ca.on.hojat.gamenews.core.domain.games.ObservableGamesUseCase
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This interface is coupled with a UseCase that returns a list of Games;
 * so that's why I'm not renaming it. It can only be used for getting a
 * list of liked games.
 */
internal interface ObserveLikedGamesUseCase : ObservableGamesUseCase

@Singleton
@BindType
internal class ObserveLikedGamesUseCaseImpl @Inject constructor(
    private val likedGamesLocalDataSource: LikedGamesLocalDataSource,
    private val dispatcherProvider: DispatcherProvider,
) : ObserveLikedGamesUseCase {

    override fun execute(params: ObserveUseCaseParams): Flow<List<Game>> {
        return likedGamesLocalDataSource.observeLikedGames(params.pagination)
            .flowOn(dispatcherProvider.main)
    }
}
