package ca.on.hojat.gamenews.feature_likes.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ca.on.hojat.gamenews.shared.ui.CommandsHandler
import ca.on.hojat.gamenews.shared.ui.NavBarColorHandler
import ca.on.hojat.gamenews.shared.ui.RoutesHandler
import ca.on.hojat.gamenews.shared.ui.base.events.Route
import ca.on.hojat.gamenews.shared.ui.theme.GameNewsTheme
import ca.on.hojat.gamenews.shared.ui.widgets.games.GameUiModel
import ca.on.hojat.gamenews.shared.ui.widgets.games.Games
import ca.on.hojat.gamenews.shared.ui.widgets.games.GamesUiState
import ca.on.hojat.gamenews.shared.ui.widgets.toolbars.Toolbar
import ca.on.hojat.gamenews.R

@Composable
fun LikedGames(
    modifier: Modifier,
    onRoute: (Route) -> Unit,
) {
    LikedGames(
        viewModel = hiltViewModel(),
        modifier = modifier,
        onRoute = onRoute,
    )
}

@Composable
private fun LikedGames(
    viewModel: LikedGamesViewModel,
    modifier: Modifier,
    onRoute: (Route) -> Unit,
) {
    NavBarColorHandler()
    CommandsHandler(viewModel = viewModel)
    RoutesHandler(viewModel = viewModel, onRoute = onRoute)
    LikedGames(
        uiState = viewModel.uiState.collectAsState().value,
        onSearchButtonClicked = viewModel::onSearchButtonClicked,
        onGameClicked = viewModel::onGameClicked,
        onBottomReached = viewModel::onBottomReached,
        modifier = modifier,
    )
}

@Composable
private fun LikedGames(
    uiState: GamesUiState,
    onSearchButtonClicked: () -> Unit,
    onGameClicked: (GameUiModel) -> Unit,
    onBottomReached: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Toolbar(
                title = stringResource(R.string.liked_games_toolbar_title),
                contentPadding = WindowInsets.statusBars
                    .only(WindowInsetsSides.Vertical + WindowInsetsSides.Horizontal)
                    .asPaddingValues(),
                rightButtonIcon = painterResource(R.drawable.magnify),
                onRightButtonClick = onSearchButtonClicked,
            )
        },
    ) { paddingValues ->
        Games(
            uiState = uiState,
            modifier = Modifier.padding(paddingValues),
            onGameClicked = onGameClicked,
            onBottomReached = onBottomReached,
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun LikedGamesPreview() {
    GameNewsTheme {
        LikedGames(
            uiState = GamesUiState(
                isLoading = false,
                infoIconId = R.drawable.gamepad_variant_outline,
                infoTitle = "No Games\nNo Games",
                games = emptyList(),
            ),
            onSearchButtonClicked = {},
            onGameClicked = {},
            onBottomReached = {},
        )
    }
}
