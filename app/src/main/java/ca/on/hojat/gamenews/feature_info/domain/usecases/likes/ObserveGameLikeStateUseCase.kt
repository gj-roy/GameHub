package ca.on.hojat.gamenews.feature_info.domain.usecases.likes

import ca.on.hojat.gamenews.shared.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.shared.domain.common.usecases.ObservableUseCase
import ca.on.hojat.gamenews.shared.domain.games.datastores.LikedGamesLocalDataStore
import ca.on.hojat.gamenews.feature_info.domain.usecases.likes.ObserveGameLikeStateUseCase.Params
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

internal interface ObserveGameLikeStateUseCase : ObservableUseCase<Params, Boolean> {

    data class Params(val gameId: Int)
}

@Singleton
@BindType
internal class ObserveGameLikeStateUseCaseImpl @Inject constructor(
    private val likedGamesLocalDataStore: LikedGamesLocalDataStore,
    private val dispatcherProvider: DispatcherProvider,
) : ObserveGameLikeStateUseCase {

    override fun execute(params: Params): Flow<Boolean> {
        return likedGamesLocalDataStore.observeGameLikeState(params.gameId)
            .flowOn(dispatcherProvider.main)
    }
}
