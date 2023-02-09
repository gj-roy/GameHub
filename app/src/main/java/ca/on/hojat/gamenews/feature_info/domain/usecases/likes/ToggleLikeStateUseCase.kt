package ca.on.hojat.gamenews.feature_info.domain.usecases.likes

import ca.on.hojat.gamenews.core.domain.common.usecases.UseCase
import ca.on.hojat.gamenews.core.domain.games.repository.LikedGamesLocalDataSource
import ca.on.hojat.gamenews.feature_info.domain.usecases.likes.ToggleLikeStateUseCase.Params
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject
import javax.inject.Singleton

internal interface ToggleLikeStateUseCase : UseCase<Params, Unit> {
    data class Params(val id: Int)
}

@Singleton
@BindType
internal class ToggleLikeStateUseCaseImpl @Inject constructor(
    private val likedGamesLocalDataSource: LikedGamesLocalDataSource,
) : ToggleLikeStateUseCase {

    override suspend fun execute(params: Params) {
        if (likedGamesLocalDataSource.isGameLiked(params.id)) {
            likedGamesLocalDataSource.unlikeGame(params.id)
        } else {
            likedGamesLocalDataSource.likeGame(params.id)
        }
    }
}
