package ca.on.hojat.gamenews.feature_info.presentation.widgets.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.common_ui.CommandsHandler
import ca.on.hojat.gamenews.common_ui.LocalUrlOpener
import ca.on.hojat.gamenews.common_ui.NavBarColorHandler
import ca.on.hojat.gamenews.common_ui.RoutesHandler
import ca.on.hojat.gamenews.common_ui.base.events.Route
import ca.on.hojat.gamenews.common_ui.theme.GameHubTheme
import ca.on.hojat.gamenews.common_ui.widgets.AnimatedContentContainer
import ca.on.hojat.gamenews.common_ui.widgets.FiniteUiState
import ca.on.hojat.gamenews.common_ui.widgets.GameNewsProgressIndicator
import ca.on.hojat.gamenews.common_ui.widgets.Info
import ca.on.hojat.gamenews.common_ui.widgets.categorypreview.GamesCategoryPreview
import ca.on.hojat.gamenews.core.extensions.showShortToast
import ca.on.hojat.gamenews.feature_info.presentation.InfoScreenCommand
import ca.on.hojat.gamenews.feature_info.presentation.InfoScreenViewModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.InfoScreenSummary
import ca.on.hojat.gamenews.feature_info.presentation.widgets.companies.InfoScreenCompanies
import ca.on.hojat.gamenews.feature_info.presentation.widgets.companies.InfoScreenCompanyUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.details.GameInfoDetails
import ca.on.hojat.gamenews.feature_info.presentation.widgets.details.GameInfoDetailsUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.header.GameInfoHeader
import ca.on.hojat.gamenews.feature_info.presentation.widgets.header.InfoScreenHeaderUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.header.artworks.InfoScreenArtworkUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.links.GameInfoLinkUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.links.GameInfoLinks
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.GameInfoRelatedGameUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.GameInfoRelatedGamesType
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.GameInfoRelatedGamesUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.mapToCategoryUiModels
import ca.on.hojat.gamenews.feature_info.presentation.widgets.relatedgames.mapToInfoRelatedGameUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.screenshots.InfoScreenShotSection
import ca.on.hojat.gamenews.feature_info.presentation.widgets.screenshots.InfoScreenShotUiModel
import ca.on.hojat.gamenews.feature_info.presentation.widgets.videos.InfoScreenVideoSection
import ca.on.hojat.gamenews.feature_info.presentation.widgets.videos.InfoScreenVideoUiModel

@Composable
fun InfoScreen(onRoute: (Route) -> Unit) {
    InfoScreen(
        viewModel = hiltViewModel(),
        onRoute = onRoute,
    )
}

@Composable
private fun InfoScreen(
    viewModel: InfoScreenViewModel,
    onRoute: (Route) -> Unit,
) {
    val urlOpener = LocalUrlOpener.current
    val context = LocalContext.current

    NavBarColorHandler()
    CommandsHandler(viewModel = viewModel) { command ->
        when (command) {
            is InfoScreenCommand.OpenUrl -> {
                if (!urlOpener.openUrl(command.url, context)) {
                    context.showShortToast(context.getString(R.string.url_opener_not_found))
                }
            }
        }
    }
    RoutesHandler(viewModel = viewModel, onRoute = onRoute)
    InfoScreen(
        uiState = viewModel.uiState.collectAsState().value,
        onArtworkClicked = viewModel::onArtworkClicked,
        onBackButtonClicked = viewModel::onBackButtonClicked,
        onCoverClicked = viewModel::onCoverClicked,
        onLikeButtonClicked = viewModel::onLikeButtonClicked,
        onVideoClicked = viewModel::onVideoClicked,
        onScreenshotClicked = viewModel::onScreenshotClicked,
        onLinkClicked = viewModel::onLinkClicked,
        onCompanyClicked = viewModel::onCompanyClicked,
        onRelatedGameClicked = viewModel::onRelatedGameClicked,
    )
}

@Composable
private fun InfoScreen(
    uiState: GameInfoUiState,
    onArtworkClicked: (artworkIndex: Int) -> Unit,
    onBackButtonClicked: () -> Unit,
    onCoverClicked: () -> Unit,
    onLikeButtonClicked: () -> Unit,
    onVideoClicked: (InfoScreenVideoUiModel) -> Unit,
    onScreenshotClicked: (screenshotIndex: Int) -> Unit,
    onLinkClicked: (GameInfoLinkUiModel) -> Unit,
    onCompanyClicked: (InfoScreenCompanyUiModel) -> Unit,
    onRelatedGameClicked: (GameInfoRelatedGameUiModel) -> Unit,
) {
    Scaffold { paddingValues ->
        AnimatedContentContainer(
            finiteUiState = uiState.finiteUiState,
            modifier = Modifier.padding(paddingValues),
        ) { finiteUiState ->
            when (finiteUiState) {
                FiniteUiState.Empty -> {
                    EmptyState(
                        modifier = Modifier
                            .systemBarsPadding()
                            .align(Alignment.Center),
                    )
                }
                FiniteUiState.Loading -> {
                    LoadingState(
                        modifier = Modifier
                            .systemBarsPadding()
                            .align(Alignment.Center),
                    )
                }
                FiniteUiState.Success -> {
                    SuccessState(
                        gameInfo = checkNotNull(uiState.game),
                        modifier = Modifier.navigationBarsPadding(),
                        onArtworkClicked = onArtworkClicked,
                        onBackButtonClicked = onBackButtonClicked,
                        onCoverClicked = onCoverClicked,
                        onLikeButtonClicked = onLikeButtonClicked,
                        onVideoClicked = onVideoClicked,
                        onScreenshotClicked = onScreenshotClicked,
                        onLinkClicked = onLinkClicked,
                        onCompanyClicked = onCompanyClicked,
                        onRelatedGameClicked = onRelatedGameClicked,
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingState(modifier: Modifier) {
    GameNewsProgressIndicator(modifier = modifier)
}

@Composable
private fun EmptyState(modifier: Modifier) {
    Info(
        icon = painterResource(R.drawable.gamepad_variant_outline),
        title = stringResource(R.string.game_info_info_view_title),
        modifier = modifier.padding(horizontal = GameHubTheme.spaces.spacing_7_5),
    )
}

@Composable
private fun SuccessState(
    gameInfo: InfoScreenUiModel,
    modifier: Modifier,
    onArtworkClicked: (artworkIndex: Int) -> Unit,
    onBackButtonClicked: () -> Unit,
    onCoverClicked: () -> Unit,
    onLikeButtonClicked: () -> Unit,
    onVideoClicked: (InfoScreenVideoUiModel) -> Unit,
    onScreenshotClicked: (screenshotIndex: Int) -> Unit,
    onLinkClicked: (GameInfoLinkUiModel) -> Unit,
    onCompanyClicked: (InfoScreenCompanyUiModel) -> Unit,
    onRelatedGameClicked: (GameInfoRelatedGameUiModel) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Content(
            gameInfo = gameInfo,
            modifier = modifier,
            onArtworkClicked = onArtworkClicked,
            onBackButtonClicked = onBackButtonClicked,
            onCoverClicked = onCoverClicked,
            onLikeButtonClicked = onLikeButtonClicked,
            onVideoClicked = onVideoClicked,
            onScreenshotClicked = onScreenshotClicked,
            onLinkClicked = onLinkClicked,
            onCompanyClicked = onCompanyClicked,
            onRelatedGameClicked = onRelatedGameClicked,
        )
    }
}

@Composable
private fun Content(
    gameInfo: InfoScreenUiModel,
    modifier: Modifier,
    onArtworkClicked: (artworkIndex: Int) -> Unit,
    onBackButtonClicked: () -> Unit,
    onCoverClicked: () -> Unit,
    onLikeButtonClicked: () -> Unit,
    onVideoClicked: (InfoScreenVideoUiModel) -> Unit,
    onScreenshotClicked: (screenshotIndex: Int) -> Unit,
    onLinkClicked: (GameInfoLinkUiModel) -> Unit,
    onCompanyClicked: (InfoScreenCompanyUiModel) -> Unit,
    onRelatedGameClicked: (GameInfoRelatedGameUiModel) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(GameHubTheme.spaces.spacing_3_5),
    ) {
        headerItem(
            model = gameInfo.headerModel,
            onArtworkClicked = onArtworkClicked,
            onBackButtonClicked = onBackButtonClicked,
            onCoverClicked = onCoverClicked,
            onLikeButtonClicked = onLikeButtonClicked,
        )

        if (gameInfo.hasVideos) {
            videosItem(
                videos = gameInfo.videoModels,
                onVideoClicked = onVideoClicked,
            )
        }

        if (gameInfo.hasScreenshots) {
            screenshotsItem(
                screenshots = gameInfo.screenshotModels,
                onScreenshotClicked = onScreenshotClicked,
            )
        }

        if (gameInfo.hasSummary) {
            summaryItem(model = checkNotNull(gameInfo.summary))
        }

        if (gameInfo.hasDetails) {
            detailsItem(model = checkNotNull(gameInfo.detailsModel))
        }

        if (gameInfo.hasLinks) {
            linksItem(
                model = gameInfo.linkModels,
                onLinkClicked = onLinkClicked,
            )
        }

        if (gameInfo.hasCompanies) {
            companiesItem(
                model = gameInfo.companyModels,
                onCompanyClicked = onCompanyClicked,
            )
        }

        if (gameInfo.hasOtherCompanyGames) {
            relatedGamesItem(
                model = checkNotNull(gameInfo.otherCompanyGames),
                onGameClicked = onRelatedGameClicked,
            )
        }

        if (gameInfo.hasSimilarGames) {
            relatedGamesItem(
                model = checkNotNull(gameInfo.similarGames),
                onGameClicked = onRelatedGameClicked,
            )
        }
    }
}

private fun LazyListScope.headerItem(
    model: InfoScreenHeaderUiModel,
    onArtworkClicked: (artworkIndex: Int) -> Unit,
    onBackButtonClicked: () -> Unit,
    onCoverClicked: () -> Unit,
    onLikeButtonClicked: () -> Unit,
) {
    gameInfoItem(item = GameInfoItem.Header) {
        GameInfoHeader(
            headerInfo = model,
            onArtworkClicked = onArtworkClicked,
            onBackButtonClicked = onBackButtonClicked,
            onCoverClicked = onCoverClicked,
            onLikeButtonClicked = onLikeButtonClicked,
        )
    }
}

private fun LazyListScope.videosItem(
    videos: List<InfoScreenVideoUiModel>,
    onVideoClicked: (InfoScreenVideoUiModel) -> Unit,
) {
    gameInfoItem(item = GameInfoItem.Videos) {
        InfoScreenVideoSection(
            videos = videos,
            onVideClicked = onVideoClicked,
        )
    }
}

private fun LazyListScope.screenshotsItem(
    screenshots: List<InfoScreenShotUiModel>,
    onScreenshotClicked: (screenshotIndex: Int) -> Unit,
) {
    gameInfoItem(item = GameInfoItem.Screenshots) {
        InfoScreenShotSection(
            screenshots = screenshots,
            onScreenshotClicked = onScreenshotClicked,
        )
    }
}

private fun LazyListScope.summaryItem(model: String) {
    gameInfoItem(item = GameInfoItem.Summary) {
        InfoScreenSummary(summary = model)
    }
}

private fun LazyListScope.detailsItem(model: GameInfoDetailsUiModel) {
    gameInfoItem(item = GameInfoItem.Details) {
        GameInfoDetails(details = model)
    }
}

private fun LazyListScope.linksItem(
    model: List<GameInfoLinkUiModel>,
    onLinkClicked: (GameInfoLinkUiModel) -> Unit,
) {
    gameInfoItem(item = GameInfoItem.Links) {
        GameInfoLinks(
            links = model,
            onLinkClicked = onLinkClicked,
        )
    }
}

private fun LazyListScope.companiesItem(
    model: List<InfoScreenCompanyUiModel>,
    onCompanyClicked: (InfoScreenCompanyUiModel) -> Unit,
) {
    gameInfoItem(item = GameInfoItem.Companies) {
        InfoScreenCompanies(
            companies = model,
            onCompanyClicked = onCompanyClicked,
        )
    }
}

private fun LazyListScope.relatedGamesItem(
    model: GameInfoRelatedGamesUiModel,
    onGameClicked: (GameInfoRelatedGameUiModel) -> Unit,
) {
    gameInfoItem(
        item = when (model.type) {
            GameInfoRelatedGamesType.OTHER_COMPANY_GAMES -> GameInfoItem.OtherCompanyGames
            GameInfoRelatedGamesType.SIMILAR_GAMES -> GameInfoItem.SimilarGames
        }
    ) {
        val categoryGames = remember(model.items) {
            model.items.mapToCategoryUiModels()
        }

        GamesCategoryPreview(
            title = model.title,
            isProgressBarVisible = false,
            games = categoryGames,
            onCategoryGameClicked = {
                onGameClicked(it.mapToInfoRelatedGameUiModel())
            },
            topBarMargin = GameHubTheme.spaces.spacing_2_5,
            isMoreButtonVisible = false,
        )
    }
}

private fun LazyListScope.gameInfoItem(
    item: GameInfoItem,
    content: @Composable LazyItemScope.() -> Unit,
) {
    item(
        key = item.key,
        contentType = item.contentType,
        content = content,
    )
}

private enum class GameInfoItem(
    val key: Int,
    val contentType: Int,
) {
    Header(key = 1, contentType = 1),
    Videos(key = 2, contentType = 2),
    Screenshots(key = 3, contentType = 3),
    Summary(key = 4, contentType = 4),
    Details(key = 5, contentType = 5),
    Links(key = 6, contentType = 6),
    Companies(key = 7, contentType = 7),

    // Both other & similar games is the same composable
    // filled with different data. That's why contentType
    // is the same for them two.
    OtherCompanyGames(key = 8, contentType = 8),
    SimilarGames(key = 9, contentType = 8),
}

// TODO (02.01.2022): Currently, preview height is limited to 2k DP.
// Try to increase this value in the future to showcase all the UI.
@Preview(heightDp = 2000)
@Preview(heightDp = 2000, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GameInfoSuccessStateWithMaxUiElementsPreview() {
    GameHubTheme {
        InfoScreen(
            uiState = GameInfoUiState(
                isLoading = false,
                game = buildFakeGameModel(),
            ),
            onArtworkClicked = {},
            onBackButtonClicked = {},
            onCoverClicked = {},
            onLikeButtonClicked = {},
            onVideoClicked = {},
            onScreenshotClicked = {},
            onLinkClicked = {},
            onCompanyClicked = {},
            onRelatedGameClicked = {},
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GameInfoSuccessStateWithMinUiElementsPreview() {
    val game = buildFakeGameModel()
    val strippedGame = game.copy(
        headerModel = game.headerModel.copy(
            developerName = null,
            likeCount = "0",
            gameCategory = "N/A",
        ),
        videoModels = emptyList(),
        screenshotModels = emptyList(),
        summary = null,
        detailsModel = null,
        linkModels = emptyList(),
        companyModels = emptyList(),
        otherCompanyGames = null,
        similarGames = null,
    )

    GameHubTheme {
        InfoScreen(
            uiState = GameInfoUiState(
                isLoading = false,
                game = strippedGame,
            ),
            onArtworkClicked = {},
            onBackButtonClicked = {},
            onCoverClicked = {},
            onLikeButtonClicked = {},
            onVideoClicked = {},
            onScreenshotClicked = {},
            onLinkClicked = {},
            onCompanyClicked = {},
            onRelatedGameClicked = {},
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GameInfoEmptyStatePreview() {
    GameHubTheme {
        InfoScreen(
            uiState = GameInfoUiState(
                isLoading = false,
                game = null,
            ),
            onArtworkClicked = {},
            onBackButtonClicked = {},
            onCoverClicked = {},
            onLikeButtonClicked = {},
            onVideoClicked = {},
            onScreenshotClicked = {},
            onLinkClicked = {},
            onCompanyClicked = {},
            onRelatedGameClicked = {},
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GameInfoLoadingStatePreview() {
    GameHubTheme {
        InfoScreen(
            uiState = GameInfoUiState(
                isLoading = true,
                game = null,
            ),
            onArtworkClicked = {},
            onBackButtonClicked = {},
            onCoverClicked = {},
            onLikeButtonClicked = {},
            onVideoClicked = {},
            onScreenshotClicked = {},
            onLinkClicked = {},
            onCompanyClicked = {},
            onRelatedGameClicked = {},
        )
    }
}

@Suppress("LongMethod")
private fun buildFakeGameModel(): InfoScreenUiModel {
    return InfoScreenUiModel(
        id = 1,
        headerModel = InfoScreenHeaderUiModel(
            artworks = listOf(InfoScreenArtworkUiModel.DefaultImage),
            isLiked = true,
            coverImageUrl = null,
            title = "Elden Ring",
            releaseDate = "Feb 25, 2022 (in a month)",
            developerName = "FromSoftware",
            rating = "N/A",
            likeCount = "92",
            ageRating = "N/A",
            gameCategory = "Main",
        ),
        videoModels = listOf(
            InfoScreenVideoUiModel(
                id = "1",
                thumbnailUrl = "",
                videoUrl = "",
                title = "Announcement Trailer",
            ),
            InfoScreenVideoUiModel(
                id = "2",
                thumbnailUrl = "",
                videoUrl = "",
                title = "Gameplay Trailer",
            ),
        ),
        screenshotModels = listOf(
            InfoScreenShotUiModel(
                id = "1",
                url = "",
            ),
            InfoScreenShotUiModel(
                id = "2",
                url = "",
            ),
        ),
        summary = "Elden Ring is an action-RPG open world game with RPG " +
                "elements such as stats, weapons and spells.",
        detailsModel = GameInfoDetailsUiModel(
            genresText = "Role-playing (RPG)",
            platformsText = "PC (Microsoft Windows) • PlayStation 4 • " +
                    "Xbox One • PlayStation 5 • Xbox Series X|S",
            modesText = "Single player • Multiplayer • Co-operative",
            playerPerspectivesText = "Third person",
            themesText = "Action",
        ),
        linkModels = listOf(
            GameInfoLinkUiModel(
                id = 1,
                text = "Steam",
                iconId = R.drawable.steam,
                url = "",
            ),
            GameInfoLinkUiModel(
                id = 2,
                text = "Official",
                iconId = R.drawable.web,
                url = "",
            ),
            GameInfoLinkUiModel(
                id = 3,
                text = "Twitter",
                iconId = R.drawable.twitter,
                url = "",
            ),
            GameInfoLinkUiModel(
                id = 4,
                text = "Subreddit",
                iconId = R.drawable.reddit,
                url = "",
            ),
            GameInfoLinkUiModel(
                id = 5,
                text = "YouTube",
                iconId = R.drawable.youtube,
                url = "",
            ),
            GameInfoLinkUiModel(
                id = 6,
                text = "Twitch",
                iconId = R.drawable.twitch,
                url = "",
            ),
        ),
        companyModels = listOf(
            InfoScreenCompanyUiModel(
                id = 1,
                logoUrl = null,
                logoWidth = 1400,
                logoHeight = 400,
                websiteUrl = "",
                name = "FromSoftware",
                roles = "Main Developer",
            ),
            InfoScreenCompanyUiModel(
                id = 2,
                logoUrl = null,
                logoWidth = 500,
                logoHeight = 400,
                websiteUrl = "",
                name = "Bandai Namco Entertainment",
                roles = "Publisher",
            ),
        ),
        otherCompanyGames = GameInfoRelatedGamesUiModel(
            type = GameInfoRelatedGamesType.OTHER_COMPANY_GAMES,
            title = "More games by FromSoftware",
            items = listOf(
                GameInfoRelatedGameUiModel(
                    id = 1,
                    title = "Dark Souls",
                    coverUrl = null,
                ),
                GameInfoRelatedGameUiModel(
                    id = 2,
                    title = "Dark Souls II",
                    coverUrl = null,
                ),
                GameInfoRelatedGameUiModel(
                    id = 3,
                    title = "Lost Kingdoms",
                    coverUrl = null,
                ),
                GameInfoRelatedGameUiModel(
                    id = 4,
                    title = "Lost Kingdoms II",
                    coverUrl = null,
                ),
            ),
        ),
        similarGames = GameInfoRelatedGamesUiModel(
            type = GameInfoRelatedGamesType.SIMILAR_GAMES,
            title = "Similar Games",
            items = listOf(
                GameInfoRelatedGameUiModel(
                    id = 1,
                    title = "Nights of Azure 2: Bride of the New Moon",
                    coverUrl = null,
                ),
                GameInfoRelatedGameUiModel(
                    id = 2,
                    title = "God Eater 3",
                    coverUrl = null,
                ),
                GameInfoRelatedGameUiModel(
                    id = 3,
                    title = "Shadows: Awakening",
                    coverUrl = null,
                ),
                GameInfoRelatedGameUiModel(
                    id = 3,
                    title = "SoulWorker",
                    coverUrl = null,
                ),
            ),
        ),
    )
}
