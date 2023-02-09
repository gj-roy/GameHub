package ca.on.hojat.gamenews.feature_info.presentation.widgets.main

import ca.on.hojat.gamenews.core.domain.entities.Game
import ca.on.hojat.gamenews.feature_info.domain.entities.GameInfo
import ca.on.hojat.gamenews.feature_info.presentation.widgets.companies.InfoScreenCompanyUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.details.GameInfoDetailsUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.header.GameInfoHeaderUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.links.GameInfoLinkUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.GameInfoRelatedGamesUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.mappers.GameInfoOtherCompanyGamesUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.mappers.GameInfoSimilarGamesUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.screenshots.GameInfoScreenshotUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.screenshots.mapToUiModels
import ca.on.hojat.gamenews.feature_info.presentation.widgets.videos.GameInfoVideoUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.videos.mapToUiModels
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

internal interface GameInfoUiModelMapper {

    fun mapToUiModel(gameInfo: GameInfo): GameInfoUiModel
}

@BindType(installIn = BindType.Component.VIEW_MODEL)
@Suppress("LongParameterList")
internal class GameInfoUiModelMapperImpl @Inject constructor(
    private val headerModelMapper: GameInfoHeaderUiModelMapper,
    private val videoModelMapper: GameInfoVideoUiModelMapper,
    private val screenshotModelMapper: GameInfoScreenshotUiModelMapper,
    private val detailsModelMapper: GameInfoDetailsUiModelMapper,
    private val linkModelMapper: GameInfoLinkUiModelMapper,
    private val companyModelMapper: InfoScreenCompanyUiModelMapper,
    private val otherCompanyGamesModelMapper: GameInfoOtherCompanyGamesUiModelMapper,
    private val similarGamesModelMapper: GameInfoSimilarGamesUiModelMapper,
) : GameInfoUiModelMapper {

    override fun mapToUiModel(gameInfo: GameInfo): GameInfoUiModel {

        return GameInfoUiModel(
            id = gameInfo.game.id,
            headerModel = headerModelMapper.mapToUiModel(gameInfo.game, gameInfo.isGameLiked),
            videoModels = videoModelMapper.mapToUiModels(gameInfo.game.videos),
            screenshotModels = screenshotModelMapper.mapToUiModels(gameInfo.game.screenshots),
            summary = gameInfo.game.summary,
            detailsModel = detailsModelMapper.mapToUiModel(gameInfo.game),
            linkModels = linkModelMapper.mapToUiModels(gameInfo.game.websites),
            companyModels = companyModelMapper.mapToUiModels(gameInfo.game.involvedCompanies),
            otherCompanyGames = gameInfo.game.createOtherCompanyGamesUiModel(gameInfo.companyGames),
            similarGames = similarGamesModelMapper.mapToUiModel(gameInfo.similarGames),
        )
    }

    private fun Game.createOtherCompanyGamesUiModel(otherCompanyGames: List<Game>): GameInfoRelatedGamesUiModel? {
        return otherCompanyGamesModelMapper.mapToUiModel(otherCompanyGames, this)
    }
}
