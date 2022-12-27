package ca.on.hojat.gamenews.feature_news.presentation.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ca.on.hojat.gamenews.core.common_ui.CommandsHandler
import ca.on.hojat.gamenews.core.common_ui.NavBarColorHandler
import ca.on.hojat.gamenews.core.common_ui.theme.GameNewsTheme
import ca.on.hojat.gamenews.shared.ui.widgets.AnimatedContentContainer
import ca.on.hojat.gamenews.shared.ui.widgets.FiniteUiState
import ca.on.hojat.gamenews.shared.ui.widgets.GameNewsProgressIndicator
import ca.on.hojat.gamenews.shared.ui.widgets.Info
import ca.on.hojat.gamenews.shared.ui.widgets.RefreshableContent
import ca.on.hojat.gamenews.shared.ui.widgets.toolbars.Toolbar
import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.core.common_ui.LocalUrlOpener
import ca.on.hojat.gamenews.core.extensions.showShortToast
import ca.on.hojat.gamenews.feature_news.presentation.GamingNewsCommand
import ca.on.hojat.gamenews.feature_news.presentation.GamingNewsViewModel

@Composable
fun GamingNews(modifier: Modifier) {
    GamingNews(
        viewModel = hiltViewModel(),
        modifier = modifier,
    )
}

@Composable
private fun GamingNews(
    viewModel: GamingNewsViewModel,
    modifier: Modifier,
) {
    val urlOpener = LocalUrlOpener.current
    val context = LocalContext.current

    NavBarColorHandler()
    CommandsHandler(viewModel = viewModel) { command ->
        when (command) {
            is GamingNewsCommand.OpenUrl -> {
                if (!urlOpener.openUrl(command.url, context)) {
                    context.showShortToast(context.getString(R.string.url_opener_not_found))
                }
            }
        }
    }
    GamingNews(
        uiState = viewModel.uiState.collectAsState().value,
        onNewsItemClicked = viewModel::onNewsItemClicked,
        onRefreshRequested = viewModel::onRefreshRequested,
        modifier = modifier,
    )
}

@Composable
private fun GamingNews(
    uiState: GamingNewsUiState,
    onNewsItemClicked: (GamingNewsItemUiModel) -> Unit,
    onRefreshRequested: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Toolbar(
                title = stringResource(R.string.gaming_news_toolbar_title),
                contentPadding = WindowInsets.statusBars
                    .only(WindowInsetsSides.Vertical + WindowInsetsSides.Horizontal)
                    .asPaddingValues(),
            )
        },
    ) { paddingValues ->
        AnimatedContentContainer(
            finiteUiState = uiState.finiteUiState,
            modifier = Modifier.padding(paddingValues),
        ) { finiteUiState ->
            when (finiteUiState) {
                FiniteUiState.Loading -> {
                    LoadingState(modifier = Modifier.align(Alignment.Center))
                }
                else -> {
                    RefreshableContent(
                        isRefreshing = uiState.isRefreshing,
                        modifier = Modifier.matchParentSize(),
                        onRefreshRequested = onRefreshRequested,
                    ) {
                        if (finiteUiState == FiniteUiState.Empty) {
                            EmptyState(modifier = Modifier.matchParentSize())
                        } else {
                            SuccessState(
                                news = uiState.news,
                                onNewsItemClicked = onNewsItemClicked,
                            )
                        }
                    }
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
    Column(
        // verticalScroll is to enable SwipeRefresh to work
        // when the screen is in empty state
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Info(
            icon = painterResource(R.drawable.newspaper_variant_outline),
            title = stringResource(R.string.gaming_news_info_view_title),
            modifier = Modifier.padding(
                horizontal = GameNewsTheme.spaces.spacing_7_5,
            ),
        )
    }
}

@Composable
private fun SuccessState(
    news: List<GamingNewsItemUiModel>,
    onNewsItemClicked: (GamingNewsItemUiModel) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(GameNewsTheme.spaces.spacing_3_5),
    ) {
        items(items = news, key = GamingNewsItemUiModel::id) { itemModel ->
            GamingNewsItem(
                model = itemModel,
                onClick = { onNewsItemClicked(itemModel) }
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GamingNewsSuccessStatePreview() {
    val news = listOf(
        GamingNewsItemUiModel(
            id = 1,
            imageUrl = "",
            title = "Halo Infinite Season 1 Will Run Until May 2022",
            lede = "Season 1 has been extended until May 2020, which " +
                    "might mean campaign co-op and Forge are coming even later than expected.",
            publicationDate = "3 mins ago",
            siteDetailUrl = "url",
        ),
        GamingNewsItemUiModel(
            id = 2,
            imageUrl = "",
            title = "Call of Duty: Vanguard's UK Launch Sales are Down 40% From Last Year",
            lede = "Call of Duty: Vanguard's launch sales are down about 40% compared to last year's " +
                    "Call of Duty: Black Ops Cold War in the UK.",
            publicationDate = "an hour ago",
            siteDetailUrl = "url",
        ),
        GamingNewsItemUiModel(
            id = 3,
            imageUrl = null,
            title = "WoW Classic Season of Mastery: Full List of Changes",
            lede = "World of Warcraft Classic's first season is nearly here, and Blizzard has " +
                    "detailed all the changes players can expect.",
            publicationDate = "2 hours ago",
            siteDetailUrl = "url",
        ),
    )

    GameNewsTheme {
        GamingNews(
            uiState = GamingNewsUiState(
                news = news,
            ),
            onNewsItemClicked = {},
            onRefreshRequested = {},
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GamingNewsEmptyStatePreview() {
    GameNewsTheme {
        GamingNews(
            uiState = GamingNewsUiState(),
            onNewsItemClicked = {},
            onRefreshRequested = {},
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GamingNewsLoadingStatePreview() {
    GameNewsTheme {
        GamingNews(
            uiState = GamingNewsUiState(isLoading = true),
            onNewsItemClicked = {},
            onRefreshRequested = {},
        )
    }
}
