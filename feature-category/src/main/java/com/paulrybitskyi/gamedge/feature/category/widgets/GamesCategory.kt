package com.paulrybitskyi.gamedge.feature.category.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ca.on.hojat.gamenews.shared.ui.CommandsHandler
import ca.on.hojat.gamenews.shared.ui.NavBarColorHandler
import ca.on.hojat.gamenews.shared.ui.RoutesHandler
import ca.on.hojat.gamenews.shared.ui.base.events.Route
import ca.on.hojat.gamenews.shared.ui.theme.GamedgeTheme
import ca.on.hojat.gamenews.shared.ui.widgets.AnimatedContentContainer
import ca.on.hojat.gamenews.shared.ui.widgets.FiniteUiState
import ca.on.hojat.gamenews.shared.ui.widgets.GamedgeProgressIndicator
import ca.on.hojat.gamenews.shared.ui.widgets.RefreshableContent
import ca.on.hojat.gamenews.shared.ui.widgets.GameCover
import ca.on.hojat.gamenews.shared.ui.widgets.Info
import ca.on.hojat.gamenews.shared.ui.widgets.toolbars.Toolbar
import com.paulrybitskyi.gamedge.feature.category.GamesCategoryViewModel
import com.paulrybitskyi.gamedge.feature.category.R

@Composable
fun GamesCategory(onRoute: (Route) -> Unit) {
    GamesCategory(
        viewModel = hiltViewModel(),
        onRoute = onRoute,
    )
}

@Composable
private fun GamesCategory(
    viewModel: GamesCategoryViewModel,
    onRoute: (Route) -> Unit,
) {
    NavBarColorHandler()
    CommandsHandler(viewModel = viewModel)
    RoutesHandler(viewModel = viewModel, onRoute = onRoute)
    GamesCategory(
        uiState = viewModel.uiState.collectAsState().value,
        onBackButtonClicked = viewModel::onToolbarLeftButtonClicked,
        onGameClicked = viewModel::onGameClicked,
        onBottomReached = viewModel::onBottomReached,
    )
}

@Composable
private fun GamesCategory(
    uiState: GamesCategoryUiState,
    onBackButtonClicked: () -> Unit,
    onGameClicked: (GameCategoryUiModel) -> Unit,
    onBottomReached: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            Toolbar(
                title = uiState.title,
                contentPadding = WindowInsets.statusBars
                    .only(WindowInsetsSides.Vertical + WindowInsetsSides.Horizontal)
                    .asPaddingValues(),
                leftButtonIcon = painterResource(R.drawable.arrow_left),
                onLeftButtonClick = onBackButtonClicked,
            )
        },
    ) { paddingValues ->
        AnimatedContentContainer(
            finiteUiState = uiState.finiteUiState,
            modifier = Modifier.padding(paddingValues),
        ) { finiteUiState ->
            when (finiteUiState) {
                FiniteUiState.Empty -> {
                    EmptyState(modifier = Modifier.align(Alignment.Center))
                }
                FiniteUiState.Loading -> {
                    LoadingState(modifier = Modifier.align(Alignment.Center))
                }
                FiniteUiState.Success -> {
                    SuccessState(
                        uiState = uiState,
                        modifier = Modifier.matchParentSize(),
                        onGameClicked = onGameClicked,
                        onBottomReached = onBottomReached,
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingState(modifier: Modifier) {
    GamedgeProgressIndicator(modifier = modifier)
}

@Composable
private fun EmptyState(modifier: Modifier) {
    Info(
        icon = painterResource(R.drawable.gamepad_variant_outline),
        title = stringResource(R.string.games_category_info_view_title),
        modifier = modifier.padding(horizontal = GamedgeTheme.spaces.spacing_7_5),
    )
}

@Composable
private fun SuccessState(
    uiState: GamesCategoryUiState,
    modifier: Modifier,
    onGameClicked: (GameCategoryUiModel) -> Unit,
    onBottomReached: () -> Unit,
) {
    RefreshableContent(
        isRefreshing = uiState.isRefreshing,
        modifier = modifier,
        isSwipeEnabled = false,
    ) {
        VerticalGrid(
            games = uiState.games,
            onGameClicked = onGameClicked,
            onBottomReached = onBottomReached,
        )
    }
}

@Composable
private fun VerticalGrid(
    games: List<GameCategoryUiModel>,
    onGameClicked: (GameCategoryUiModel) -> Unit,
    onBottomReached: () -> Unit,
) {
    val gridConfig = rememberGamesGridConfig()
    val gridItemSpacingInPx = gridConfig.itemSpacingInPx
    val gridSpanCount = gridConfig.spanCount
    val lastIndex = games.lastIndex

    LazyVerticalGrid(columns = GridCells.Fixed(gridConfig.spanCount)) {
        itemsIndexed(items = games) { index, game ->
            if (index == lastIndex) {
                LaunchedEffect(lastIndex) {
                    onBottomReached()
                }
            }

            val column = (index % gridConfig.spanCount)
            val paddingValues = with(LocalDensity.current) {
                PaddingValues(
                    top = (if (index < gridSpanCount) gridItemSpacingInPx else 0f).toDp(),
                    bottom = gridItemSpacingInPx.toDp(),
                    start = (gridItemSpacingInPx - (column * gridItemSpacingInPx / gridSpanCount)).toDp(),
                    end = ((column + 1) * gridItemSpacingInPx / gridSpanCount).toDp(),
                )
            }

            GameCover(
                title = game.title,
                imageUrl = game.coverUrl,
                modifier = Modifier
                    .size(
                        width = gridConfig.itemWidthInDp,
                        height = gridConfig.itemHeightInDp
                    )
                    .padding(paddingValues),
                hasRoundedShape = false,
                onCoverClicked = { onGameClicked(game) },
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GamesCategorySuccessStatePreview() {
    val games = buildList {
        repeat(15) { index ->
            add(
                GameCategoryUiModel(
                    id = index + 1,
                    title = "Popular Game",
                    coverUrl = null,
                )
            )
        }
    }

    GamedgeTheme {
        GamesCategory(
            uiState = GamesCategoryUiState(
                isLoading = false,
                title = "Popular",
                games = games,
            ),
            onBackButtonClicked = {},
            onGameClicked = {},
            onBottomReached = {},
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GamesCategoryEmptyStatePreview() {
    GamedgeTheme {
        GamesCategory(
            uiState = GamesCategoryUiState(
                isLoading = false,
                title = "Popular",
                games = emptyList(),
            ),
            onBackButtonClicked = {},
            onGameClicked = {},
            onBottomReached = {},
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GamesCategoryLoadingStatePreview() {
    GamedgeTheme {
        GamesCategory(
            uiState = GamesCategoryUiState(
                isLoading = true,
                title = "Popular",
                games = emptyList(),
            ),
            onBackButtonClicked = {},
            onGameClicked = {},
            onBottomReached = {},
        )
    }
}
