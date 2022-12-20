package ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.mappers

import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.core.providers.StringProvider
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.GameInfoRelatedGameUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.GameInfoRelatedGamesType
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.GameInfoRelatedGamesUiModel
import ca.on.hojat.gamenews.shared.core.factories.IgdbImageSize
import ca.on.hojat.gamenews.shared.core.factories.IgdbImageUrlFactory
import ca.on.hojat.gamenews.shared.domain.games.entities.Game
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

internal interface GameInfoOtherCompanyGamesUiModelMapper {

    fun mapToUiModel(
        companyGames: List<Game>,
        currentGame: Game,
    ): GameInfoRelatedGamesUiModel?
}

@BindType(installIn = BindType.Component.VIEW_MODEL)
internal class GameInfoOtherCompanyGamesUiModelMapperImpl @Inject constructor(
    private val stringProvider: StringProvider,
    private val igdbImageUrlFactory: IgdbImageUrlFactory,
) : GameInfoOtherCompanyGamesUiModelMapper {

    override fun mapToUiModel(
        companyGames: List<Game>,
        currentGame: Game
    ): GameInfoRelatedGamesUiModel? {
        return companyGames
            .filter { it.id != currentGame.id }
            .takeIf(List<Game>::isNotEmpty)
            ?.let { games ->
                GameInfoRelatedGamesUiModel(
                    type = GameInfoRelatedGamesType.OTHER_COMPANY_GAMES,
                    title = currentGame.createOtherCompanyGamesModelTitle(),
                    items = games.toRelatedGameUiModels()
                )
            }
    }

    private fun Game.createOtherCompanyGamesModelTitle(): String {
        val developerName = developerCompany?.name
            ?: stringProvider.getString(R.string.game_info_other_company_games_title_default_arg)

        return stringProvider.getString(
            R.string.game_info_other_company_games_title_template,
            developerName
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
