package ca.on.hojat.gamenews.feature_info.presentation.widgets.main

import ca.on.hojat.gamenews.core.domain.entities.Game
import ca.on.hojat.gamenews.feature_info.domain.entities.InfoScreenData
import ca.on.hojat.gamenews.feature_info.presentation.widgets.companies.InfoScreenCompanyUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.details.GameInfoDetailsUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.header.GameInfoHeaderUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.links.GameInfoLinkUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.GameInfoRelatedGamesUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.mappers.GameInfoOtherCompanyGamesUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.mappers.GameInfoSimilarGamesUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.screenshots.GameInfoScreenshotUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.screenshots.mapToUiModels
import ca.on.hojat.gamenews.feature_info.presentation.widgets.videos.InfoScreenVideoUiModelMapper
import ca.on.hojat.gamenews.feature_info.presentation.widgets.videos.mapToUiModels
import com.paulrybitskyi.hiltbinder.BindType
import javax.inject.Inject

internal interface GameInfoUiModelMapper {

    fun mapToUiModel(infoScreenData: InfoScreenData): GameInfoUiModel
}

@BindType(installIn = BindType.Component.VIEW_MODEL)
@Suppress("LongParameterList")
internal class GameInfoUiModelMapperImpl @Inject constructor(
    private val headerModelMapper: GameInfoHeaderUiModelMapper,
    private val videoModelMapper: InfoScreenVideoUiModelMapper,
    private val screenshotModelMapper: GameInfoScreenshotUiModelMapper,
    private val detailsModelMapper: GameInfoDetailsUiModelMapper,
    private val linkModelMapper: GameInfoLinkUiModelMapper,
    private val companyModelMapper: InfoScreenCompanyUiModelMapper,
    private val otherCompanyGamesModelMapper: GameInfoOtherCompanyGamesUiModelMapper,
    private val similarGamesModelMapper: GameInfoSimilarGamesUiModelMapper,
) : GameInfoUiModelMapper {

    override fun mapToUiModel(infoScreenData: InfoScreenData): GameInfoUiModel {

        return GameInfoUiModel(
            id = infoScreenData.game.id,
            headerModel = headerModelMapper.mapToUiModel(infoScreenData.game, infoScreenData.isGameLiked),
            videoModels = videoModelMapper.mapToUiModels(infoScreenData.game.videos),
            screenshotModels = screenshotModelMapper.mapToUiModels(infoScreenData.game.screenshots),
            summary = infoScreenData.game.summary,
            detailsModel = detailsModelMapper.mapToUiModel(infoScreenData.game),
            linkModels = linkModelMapper.mapToUiModels(infoScreenData.game.websites),
            companyModels = companyModelMapper.mapToUiModels(infoScreenData.game.involvedCompanies),
            otherCompanyGames = infoScreenData.game.createOtherCompanyGamesUiModel(infoScreenData.companyGames),
            similarGames = similarGamesModelMapper.mapToUiModel(infoScreenData.similarGames),
        )
    }

    private fun Game.createOtherCompanyGamesUiModel(otherCompanyGames: List<Game>): GameInfoRelatedGamesUiModel? {
        return otherCompanyGamesModelMapper.mapToUiModel(otherCompanyGames, this)
    }
}
