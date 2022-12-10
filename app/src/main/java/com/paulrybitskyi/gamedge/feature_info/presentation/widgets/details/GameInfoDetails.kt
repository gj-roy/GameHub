package com.paulrybitskyi.gamedge.feature_info.presentation.widgets.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ca.on.hojat.gamenews.shared.ui.theme.GamedgeTheme
import ca.on.hojat.gamenews.shared.ui.theme.subtitle3
import com.paulrybitskyi.gamedge.R
import com.paulrybitskyi.gamedge.feature_info.presentation.widgets.utils.GameInfoSection

@Composable
internal fun GameInfoDetails(details: GameInfoDetailsUiModel) {
    GameInfoSection(
        title = stringResource(R.string.game_info_details_title),
        titleBottomPadding = GamedgeTheme.spaces.spacing_1_0,
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (details.hasGenresText) {
                CategorySection(
                    title = stringResource(R.string.game_info_details_genres_title),
                    value = checkNotNull(details.genresText),
                )
            }

            if (details.hasPlatformsText) {
                CategorySection(
                    title = stringResource(R.string.game_info_details_platforms_title),
                    value = checkNotNull(details.platformsText),
                )
            }

            if (details.hasModesText) {
                CategorySection(
                    title = stringResource(R.string.game_info_details_modes_title),
                    value = checkNotNull(details.modesText),
                )
            }

            if (details.hasPlayerPerspectivesText) {
                CategorySection(
                    title = stringResource(R.string.game_info_details_player_perspectives_title),
                    value = checkNotNull(details.playerPerspectivesText),
                )
            }

            if (details.hasThemesText) {
                CategorySection(
                    title = stringResource(R.string.game_info_details_themes_title),
                    value = checkNotNull(details.themesText),
                )
            }
        }
    }
}

@Composable
private fun CategorySection(title: String, value: String) {
    Text(
        text = title,
        modifier = Modifier.padding(top = GamedgeTheme.spaces.spacing_2_5),
        color = GamedgeTheme.colors.onPrimary,
        style = GamedgeTheme.typography.subtitle3,
    )
    Text(
        text = value,
        modifier = Modifier.padding(top = GamedgeTheme.spaces.spacing_1_0),
        style = GamedgeTheme.typography.body2,
    )
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GameInfoDetailsPreview() {
    GamedgeTheme {
        GameInfoDetails(
            details = GameInfoDetailsUiModel(
                genresText = "Adventure • Shooter • Role-playing (RPG)",
                platformsText = "PC • PS4 • XONE • PS5 • Series X • Stadia",
                modesText = "Single Player • Multiplayer",
                playerPerspectivesText = "First person • Third person",
                themesText = "Action • Science Fiction • Horror • Survival",
            ),
        )
    }
}
