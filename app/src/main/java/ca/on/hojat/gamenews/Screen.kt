package ca.on.hojat.gamenews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import ca.on.hojat.gamenews.core.extensions.toCsv
import java.net.URLEncoder

internal val START_SCREEN = Screen.Discover

internal sealed class Screen(val route: String) {
    object Discover : Screen("discover")
    object Likes : Screen("likes")
    object News : Screen("news")
    object Settings : Screen("settings")
    object Search : Screen("games-search")

    /**
     * Each one of the different categories that you can see in "Discover" page of the app.
     */
    object DiscoveryCategory : Screen("discovery-category/{${Parameters.CATEGORY}}") {
        object Parameters {
            const val CATEGORY = "category"
        }

        fun createLink(category: String): String {
            return "discovery-category/$category"
        }
    }

    object GameInfo : Screen("game-info/{${Parameters.GAME_ID}}") {
        object Parameters {
            const val GAME_ID = "game-id"
        }

        fun createLink(gameId: Int): String {
            return "game-info/$gameId"
        }
    }

    object ImageViewer : Screen(
        "image-viewer?" +
                "${Parameters.TITLE}={${Parameters.TITLE}}&" +
                "${Parameters.INITIAL_POSITION}={${Parameters.INITIAL_POSITION}}&" +
                "${Parameters.IMAGE_URLS}={${Parameters.IMAGE_URLS}}"
    ) {
        object Parameters {
            const val TITLE = "title"
            const val INITIAL_POSITION = "initial-position"
            const val IMAGE_URLS = "image-urls"
        }

        fun createLink(
            title: String?,
            initialPosition: Int,
            imageUrls: List<String>,
        ): String {
            val modifiedImageUrls = imageUrls
                .map { imageUrl -> URLEncoder.encode(imageUrl, "UTF-8") }
                .toCsv()

            return buildString {
                append("image-viewer?")

                if (title != null) {
                    append("${Parameters.TITLE}=$title&")
                }

                append("${Parameters.INITIAL_POSITION}=$initialPosition&")
                append("${Parameters.IMAGE_URLS}=$modifiedImageUrls")
            }
        }
    }

    internal companion object {

        val Saver = Saver(
            save = { it.route },
            restore = Companion::forRoute,
        )

        fun forRoute(route: String): Screen {
            return when (route) {
                Discover.route -> Discover
                Likes.route -> Likes
                News.route -> News
                Settings.route -> Settings
                Search.route -> Search
                DiscoveryCategory.route -> DiscoveryCategory
                GameInfo.route -> GameInfo
                ImageViewer.route -> ImageViewer
                else -> error("Cannot find screen for the route: $route.")
            }
        }
    }
}

@Stable
@Composable
internal fun NavController.currentScreenAsState(): State<Screen> {
    val selectedScreen = rememberSaveable(stateSaver = Screen.Saver) {
        mutableStateOf(START_SCREEN)
    }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            selectedScreen.value = Screen.forRoute(checkNotNull(destination.requireRoute()))
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedScreen
}
