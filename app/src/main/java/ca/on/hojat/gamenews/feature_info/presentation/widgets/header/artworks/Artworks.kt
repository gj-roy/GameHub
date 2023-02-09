package ca.on.hojat.gamenews.feature_info.presentation.widgets.header.artworks

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import ca.on.hojat.gamenews.R
import ca.on.hojat.gamenews.common_ui.clickable
import ca.on.hojat.gamenews.common_ui.images.defaultImageRequest
import ca.on.hojat.gamenews.common_ui.images.secondaryImage
import ca.on.hojat.gamenews.common_ui.theme.GameHubTheme
import coil.compose.AsyncImage
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@Composable
internal fun Artworks(
    artworks: List<GameInfoArtworkUiModel>,
    isScrollingEnabled: Boolean,
    modifier: Modifier,
    onArtworkChanged: (artworkIndex: Int) -> Unit,
    onArtworkClicked: (artworkIndex: Int) -> Unit,
) {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect { page -> onArtworkChanged(page) }
    }

    HorizontalPager(
        count = artworks.size,
        modifier = modifier,
        state = pagerState,
        key = { page -> artworks[page].id },
        userScrollEnabled = isScrollingEnabled,
    ) { page ->
        Artwork(
            artwork = artworks[page],
            onArtworkClicked = { onArtworkClicked(page) },
        )
    }
}

@Composable
private fun Artwork(
    artwork: GameInfoArtworkUiModel,
    onArtworkClicked: () -> Unit,
) {
    val data = when (artwork) {
        is GameInfoArtworkUiModel.DefaultImage -> R.drawable.game_background_placeholder
        is GameInfoArtworkUiModel.UrlImage -> artwork.url
    }

    AsyncImage(
        model = defaultImageRequest(data) {
            secondaryImage(R.drawable.game_background_placeholder)
        },
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                onClick = onArtworkClicked,
            ),
        contentScale = ContentScale.Crop,
    )
}

@Preview(heightDp = 240)
@Preview(heightDp = 240, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ArtworksPreview() {
    GameHubTheme {
        Artworks(
            artworks = listOf(
                GameInfoArtworkUiModel.DefaultImage,
            ),
            isScrollingEnabled = true,
            modifier = Modifier,
            onArtworkChanged = {},
            onArtworkClicked = {},
        )
    }
}
