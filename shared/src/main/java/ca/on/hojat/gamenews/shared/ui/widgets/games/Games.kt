package ca.on.hojat.gamenews.shared.ui.widgets.games

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ca.on.hojat.gamenews.shared.R
import ca.on.hojat.gamenews.core.common_ui.theme.GameNewsTheme
import ca.on.hojat.gamenews.core.common_ui.widgets.AnimatedContentContainer
import ca.on.hojat.gamenews.core.common_ui.widgets.FiniteUiState
import ca.on.hojat.gamenews.core.common_ui.widgets.GameNewsProgressIndicator
import ca.on.hojat.gamenews.core.common_ui.widgets.Info
import ca.on.hojat.gamenews.core.common_ui.widgets.RefreshableContent

@Composable
fun Games(
    uiState: GamesUiState,
    modifier: Modifier = Modifier,
    onGameClicked: (GameUiModel) -> Unit,
    onBottomReached: () -> Unit,
) {
    AnimatedContentContainer(
        finiteUiState = uiState.finiteUiState,
        modifier = modifier,
    ) { finiteUiState ->
        when (finiteUiState) {
            FiniteUiState.Empty -> {
                EmptyState(
                    uiState = uiState,
                    modifier = Modifier.align(Alignment.Center),
                )
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

@Composable
private fun LoadingState(modifier: Modifier) {
    GameNewsProgressIndicator(modifier = modifier)
}

@Composable
private fun EmptyState(
    uiState: GamesUiState,
    modifier: Modifier,
) {
    Info(
        icon = painterResource(uiState.infoIconId),
        title = uiState.infoTitle,
        modifier = modifier.padding(horizontal = GameNewsTheme.spaces.spacing_7_0),
    )
}

@Composable
private fun SuccessState(
    uiState: GamesUiState,
    modifier: Modifier,
    onGameClicked: (GameUiModel) -> Unit,
    onBottomReached: () -> Unit,
) {
    RefreshableContent(
        isRefreshing = uiState.isRefreshing,
        modifier = modifier,
        isSwipeEnabled = false,
    ) {
        val games = uiState.games
        val lastIndex = games.lastIndex

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(GameNewsTheme.spaces.spacing_3_5),
        ) {
            itemsIndexed(
                items = games,
                key = { _, game -> game.id }
            ) { index, game ->
                if (index == lastIndex) {
                    LaunchedEffect(lastIndex) {
                        onBottomReached()
                    }
                }

                Game(
                    game = game,
                    onClick = { onGameClicked(game) },
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GamesSuccessStatePreview() {
    val games = listOf(
        GameUiModel(
            id = 1,
            coverImageUrl = null,
            name = "Ghost of Tsushima: Director's Cut",
            releaseDate = "Aug 20, 2021 (2 months ago)",
            developerName = "Sucker Punch Productions",
            description = "Some very very very very very very very very very long description",
        ),
        GameUiModel(
            id = 2,
            coverImageUrl = null,
            name = "Forza Horizon 5",
            releaseDate = "Nov 09, 2021 (8 days ago)",
            developerName = "Playground Games",
            description = "Some very very very very very very very very very long description",
        ),
        GameUiModel(
            id = 3,
            coverImageUrl = null,
            name = "Outer Wilds: Echoes of the Eye",
            releaseDate = "Sep 28, 2021 (a month ago)",
            developerName = "Mobius Digital",
            description = "Some very very very very very very very very very long description",
        )
    )

    GameNewsTheme {
        Games(
            uiState = GamesUiState(
                isLoading = false,
                infoIconId = 0,
                infoTitle = "",
                games = games,
            ),
            onGameClicked = {},
            onBottomReached = {},
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GamesEmptyStatePreview() {
    GameNewsTheme {
        Games(
            uiState = GamesUiState(
                isLoading = false,
                infoIconId = R.drawable.gamepad_variant_outline,
                infoTitle = "No Games\nNo Games",
                games = emptyList(),
            ),
            onGameClicked = {},
            onBottomReached = {},
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GamesLoadingStatePreview() {
    GameNewsTheme {
        Games(
            uiState = GamesUiState(
                isLoading = true,
                infoIconId = 0,
                infoTitle = "",
                games = emptyList(),
            ),
            onGameClicked = {},
            onBottomReached = {},
        )
    }
}
