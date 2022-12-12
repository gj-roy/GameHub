package ca.on.hojat.gamenews.feature_info.domain.usecases

import ca.on.hojat.gamenews.shared.core.factories.ImageViewerGameUrlFactory
import ca.on.hojat.gamenews.shared.core.utils.onError
import ca.on.hojat.gamenews.shared.domain.common.DispatcherProvider
import ca.on.hojat.gamenews.shared.domain.common.DomainResult
import ca.on.hojat.gamenews.shared.domain.common.extensions.mapSuccess
import ca.on.hojat.gamenews.shared.domain.common.usecases.UseCase
import com.github.michaelbull.result.Err
import ca.on.hojat.gamenews.shared.domain.common.entities.Error
import ca.on.hojat.gamenews.feature_info.domain.usecases.GetGameImageUrlsUseCase.Params
import ca.on.hojat.gamenews.feature_info.domain.entities.GameImageType
import com.paulrybitskyi.hiltbinder.BindType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

internal interface GetGameImageUrlsUseCase : UseCase<Params, Flow<DomainResult<List<String>>>> {

    data class Params(
        val gameId: Int,
        val imageType: GameImageType,
    )
}

@Singleton
@BindType
internal class GetGameImageUrlsUseCaseImpl @Inject constructor(
    private val getGameUseCase: GetGameUseCase,
    private val gameUrlFactory: ImageViewerGameUrlFactory,
    private val dispatcherProvider: DispatcherProvider,
) : GetGameImageUrlsUseCase {

    override suspend fun execute(params: Params): Flow<DomainResult<List<String>>> {
        return getGameUseCase.execute(GetGameUseCase.Params(params.gameId))
            .flowOn(dispatcherProvider.main)
            .mapSuccess { game ->
                when (params.imageType) {
                    GameImageType.COVER -> {
                        gameUrlFactory.createCoverImageUrl(game)
                            ?.let(::listOf)
                            ?: error("Cannot create a game cover image url.")
                    }
                    GameImageType.ARTWORK -> gameUrlFactory.createArtworkImageUrls(game)
                    GameImageType.SCREENSHOT -> gameUrlFactory.createScreenshotImageUrls(game)
                }
            }
            .onError { error -> emit(Err(Error.Unknown(error.message.orEmpty()))) }
            .flowOn(dispatcherProvider.computation)
    }
}
