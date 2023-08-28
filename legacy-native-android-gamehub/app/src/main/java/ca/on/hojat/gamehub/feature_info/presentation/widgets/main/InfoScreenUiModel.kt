package ca.on.hojat.gamehub.feature_info.presentation.widgets.main

import androidx.compose.runtime.Immutable
import ca.on.hojat.gamehub.feature_info.presentation.widgets.companies.InfoScreenCompanyUiModel
import ca.on.hojat.gamehub.feature_info.presentation.widgets.details.InfoScreenDetailsUiModel
import ca.on.hojat.gamehub.feature_info.presentation.widgets.header.InfoScreenHeaderUiModel
import ca.on.hojat.gamehub.feature_info.presentation.widgets.links.InfoScreenLinkUiModel
import ca.on.hojat.gamehub.feature_info.presentation.widgets.relatedgames.RelatedGamesUiModel
import ca.on.hojat.gamehub.feature_info.presentation.widgets.screenshots.InfoScreenShotUiModel
import ca.on.hojat.gamehub.feature_info.presentation.widgets.videos.InfoScreenVideoUiModel

@Immutable
internal data class InfoScreenUiModel(
    val id: Int,
    val headerModel: InfoScreenHeaderUiModel,
    val videoModels: List<InfoScreenVideoUiModel>,
    val screenshotModels: List<InfoScreenShotUiModel>,
    val summary: String?,
    val detailsModel: InfoScreenDetailsUiModel?,
    val linkModels: List<InfoScreenLinkUiModel>,
    val companyModels: List<InfoScreenCompanyUiModel>,
    val otherCompanyGames: RelatedGamesUiModel?,
    val similarGames: RelatedGamesUiModel?,
) {

    val hasVideos: Boolean
        get() = videoModels.isNotEmpty()

    val hasScreenshots: Boolean
        get() = screenshotModels.isNotEmpty()

    val hasSummary: Boolean
        get() = ((summary != null) && summary.isNotBlank())

    val hasDetails: Boolean
        get() = (detailsModel != null)

    val hasLinks: Boolean
        get() = linkModels.isNotEmpty()

    val hasCompanies: Boolean
        get() = companyModels.isNotEmpty()

    val hasOtherCompanyGames: Boolean
        get() = ((otherCompanyGames != null) && otherCompanyGames.hasItems)

    val hasSimilarGames: Boolean
        get() = ((similarGames != null) && similarGames.hasItems)
}
