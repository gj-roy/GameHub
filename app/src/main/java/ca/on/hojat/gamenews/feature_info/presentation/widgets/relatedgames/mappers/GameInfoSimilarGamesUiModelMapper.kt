package ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.mappers

import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.core.providers.StringProvider
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.GameInfoRelatedGameUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.GameInfoRelatedGamesType
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.GameInfoRelatedGamesUiModel
import ca.on.hojat.gamenews.core.factories.IgdbImageSize
import ca.on.hojat.gamenews.core.factories.IgdbImageUrlFactory
import ca.on.hojat.gamenews.core.domain.entities.Game
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

internal interface GameInfoSimilarGamesUiModelMapper {
    fun mapToUiModel(similarGames: List<Game>): GameInfoRelatedGamesUiModel?
}

@BindType(installIn = BindType.Component.VIEW_MODEL)
internal class GameInfoSimilarGamesUiModelMapperImpl @Inject constructor(
    private val stringProvider: StringProvider,
    private val igdbImageUrlFactory: IgdbImageUrlFactory,
) : GameInfoSimilarGamesUiModelMapper {

    override fun mapToUiModel(similarGames: List<Game>): GameInfoRelatedGamesUiModel? {
        if (similarGames.isEmpty()) return null

        return GameInfoRelatedGamesUiModel(
            type = GameInfoRelatedGamesType.SIMILAR_GAMES,
            title = stringProvider.getString(R.string.game_info_similar_games_title),
            items = similarGames.toRelatedGameUiModels(),
        )
    }

    private fun List<Game>.toRelatedGameUiModels(): List<GameInfoRelatedGameUiModel> {
        return map {
            GameInfoRelatedGameUiModel(
                id = it.id,
                title = it.name,
                coverUrl = it.cover?.let { cover ->
                    igdbImageUrlFactory.createUrl(
                        cover,
                        IgdbImageUrlFactory.Config(IgdbImageSize.BIG_COVER)
                    )
                }
            )
        }
    }
}
