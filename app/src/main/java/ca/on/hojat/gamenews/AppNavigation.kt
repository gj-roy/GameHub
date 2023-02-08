package ca.on.hojat.gamenews

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ca.on.hojat.gamenews.common_ui.HorizontalSliding
import ca.on.hojat.gamenews.common_ui.OvershootScaling
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import ca.on.hojat.gamenews.feature_category.GamesCategoryRoute
import ca.on.hojat.gamenews.feature_category.widgets.GamesCategory
import ca.on.hojat.gamenews.feature_discovery.GamesDiscoveryRoute
import ca.on.hojat.gamenews.feature_discovery.widgets.GamesDiscovery
import ca.on.hojat.gamenews.feature_news.presentation.widgets.GamingNews
import ca.on.hojat.gamenews.feature_settings.presentation.Settings
import ca.on.hojat.gamenews.feature_image_viewer.ImageViewer
import ca.on.hojat.gamenews.feature_image_viewer.ImageViewerRoute
import ca.on.hojat.gamenews.feature_info.presentation.GameInfoRoute
import ca.on.hojat.gamenews.feature_info.presentation.widgets.main.GameInfo
import ca.on.hojat.gamenews.feature_likes.presentation.LikedGames
import ca.on.hojat.gamenews.feature_likes.presentation.LikedGamesRoute
import ca.on.hojat.gamenews.feature_search.presentation.GamesSearch
import ca.on.hojat.gamenews.feature_search.presentation.GamesSearchRoute

@Composable
internal fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = START_SCREEN.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        discoverScreen(
            navController = navController,
            modifier = modifier,
        )
        likesScreen(
            navController = navController,
            modifier = modifier,
        )
        newsScreen(modifier = modifier)
        settingsScreen(modifier = modifier)
        gamesSearchScreen(navController = navController)
        gamesCategoryScreen(navController = navController)
        gameInfoScreen(navController = navController)
        imageViewerScreen(navController = navController)
    }
}

private fun NavGraphBuilder.discoverScreen(
    navController: NavHostController,
    modifier: Modifier,
) {
    composable(
        route = Screen.Discover.route,
        enterTransition = { null },
        exitTransition = {
            when (Screen.forRoute(targetState.destination.requireRoute())) {
                Screen.Search -> OvershootScaling.exit()
                Screen.DiscoveryCategory,
                Screen.GameInfo -> HorizontalSliding.exit()
                else -> null
            }
        },
        popEnterTransition = {
            when (Screen.forRoute(initialState.destination.requireRoute())) {
                Screen.Search -> OvershootScaling.popEnter()
                Screen.DiscoveryCategory,
                Screen.GameInfo -> HorizontalSliding.popEnter()
                else -> null
            }
        },
        popExitTransition = { null },
    ) {
        GamesDiscovery(modifier) { route ->
            when (route) {
                is GamesDiscoveryRoute.Search -> {
                    navController.navigate(Screen.Search.route)
                }
                is GamesDiscoveryRoute.Category -> {
                    navController.navigate(Screen.DiscoveryCategory.createLink(route.category))
                }
                is GamesDiscoveryRoute.Info -> {
                    navController.navigate(Screen.GameInfo.createLink(route.gameId))
                }
            }
        }
    }
}

private fun NavGraphBuilder.likesScreen(
    navController: NavHostController,
    modifier: Modifier,
) {
    composable(
        route = Screen.Likes.route,
        enterTransition = { null },
        exitTransition = {
            when (Screen.forRoute(targetState.destination.requireRoute())) {
                Screen.Search -> OvershootScaling.exit()
                Screen.GameInfo -> HorizontalSliding.exit()
                else -> null
            }
        },
        popEnterTransition = {
            when (Screen.forRoute(initialState.destination.requireRoute())) {
                Screen.Search -> OvershootScaling.popEnter()
                Screen.GameInfo -> HorizontalSliding.popEnter()
                else -> null
            }
        },
        popExitTransition = { null },
    ) {
        LikedGames(modifier) { route ->
            when (route) {
                is LikedGamesRoute.Search -> {
                    navController.navigate(Screen.Search.route)
                }
                is LikedGamesRoute.Info -> {
                    navController.navigate(Screen.GameInfo.createLink(route.gameId))
                }
            }
        }
    }
}

private fun NavGraphBuilder.newsScreen(modifier: Modifier) {
    composable(
        route = Screen.News.route,
    ) {
        GamingNews(modifier)
    }
}

private fun NavGraphBuilder.settingsScreen(modifier: Modifier) {
    composable(
        route = Screen.Settings.route,
    ) {
        Settings(modifier)
    }
}

private fun NavGraphBuilder.gamesSearchScreen(navController: NavHostController) {
    composable(
        route = Screen.Search.route,
        enterTransition = {
            when (Screen.forRoute(initialState.destination.requireRoute())) {
                Screen.News,
                Screen.Discover,
                Screen.Likes -> OvershootScaling.enter()
                else -> null
            }
        },
        exitTransition = {
            when (Screen.forRoute(targetState.destination.requireRoute())) {
                Screen.GameInfo -> HorizontalSliding.exit()
                else -> null
            }
        },
        popEnterTransition = {
            when (Screen.forRoute(initialState.destination.requireRoute())) {
                Screen.GameInfo -> HorizontalSliding.popEnter()
                else -> null
            }
        },
        popExitTransition = {
            when (Screen.forRoute(targetState.destination.requireRoute())) {
                Screen.News,
                Screen.Discover,
                Screen.Likes -> OvershootScaling.popExit()
                else -> null
            }
        },
    ) {
        GamesSearch { route ->
            when (route) {
                is GamesSearchRoute.Info -> {
                    navController.navigate(Screen.GameInfo.createLink(route.gameId))
                }
                is GamesSearchRoute.Back -> {
                    navController.popBackStack()
                }
            }
        }
    }
}

private fun NavGraphBuilder.gamesCategoryScreen(navController: NavHostController) {
    composable(
        route = Screen.DiscoveryCategory.route,
        arguments = listOf(
            navArgument(Screen.DiscoveryCategory.Parameters.CATEGORY) { type = NavType.StringType },
        ),
        enterTransition = {
            when (Screen.forRoute(initialState.destination.requireRoute())) {
                Screen.Discover -> HorizontalSliding.enter()
                else -> null
            }
        },
        exitTransition = {
            when (Screen.forRoute(targetState.destination.requireRoute())) {
                Screen.GameInfo -> HorizontalSliding.exit()
                else -> null
            }
        },
        popEnterTransition = {
            when (Screen.forRoute(initialState.destination.requireRoute())) {
                Screen.GameInfo -> HorizontalSliding.popEnter()
                else -> null
            }
        },
        popExitTransition = {
            when (Screen.forRoute(targetState.destination.requireRoute())) {
                Screen.Discover -> HorizontalSliding.popExit()
                else -> null
            }
        },
    ) {
        GamesCategory { route ->
            when (route) {
                is GamesCategoryRoute.Info -> {
                    navController.navigate(Screen.GameInfo.createLink(route.gameId))
                }
                is GamesCategoryRoute.Back -> {
                    navController.popBackStack()
                }
            }
        }
    }
}

private fun NavGraphBuilder.gameInfoScreen(navController: NavHostController) {
    composable(
        route = Screen.GameInfo.route,
        arguments = listOf(
            navArgument(Screen.GameInfo.Parameters.GAME_ID) { type = NavType.IntType },
        ),
        enterTransition = {
            when (Screen.forRoute(initialState.destination.requireRoute())) {
                Screen.Discover,
                Screen.Likes,
                Screen.Search,
                Screen.DiscoveryCategory,
                Screen.GameInfo -> HorizontalSliding.enter()
                else -> null
            }
        },
        exitTransition = {
            when (Screen.forRoute(targetState.destination.requireRoute())) {
                Screen.ImageViewer,
                Screen.GameInfo -> HorizontalSliding.exit()
                else -> null
            }
        },
        popEnterTransition = {
            when (Screen.forRoute(initialState.destination.requireRoute())) {
                Screen.ImageViewer,
                Screen.GameInfo -> HorizontalSliding.popEnter()
                else -> null
            }
        },
        popExitTransition = {
            when (Screen.forRoute(targetState.destination.requireRoute())) {
                Screen.Discover,
                Screen.Likes,
                Screen.Search,
                Screen.DiscoveryCategory,
                Screen.GameInfo -> HorizontalSliding.popExit()
                else -> null
            }
        },
    ) {
        GameInfo { route ->
            when (route) {
                is GameInfoRoute.ImageViewer -> {
                    navController.navigate(
                        Screen.ImageViewer.createLink(
                            title = route.title,
                            initialPosition = route.initialPosition,
                            imageUrls = route.imageUrls,
                        )
                    )
                }
                is GameInfoRoute.Info -> {
                    navController.navigate(Screen.GameInfo.createLink(route.gameId))
                }
                is GameInfoRoute.Back -> {
                    navController.popBackStack()
                }
            }
        }
    }
}

private fun NavGraphBuilder.imageViewerScreen(navController: NavHostController) {
    composable(
        route = Screen.ImageViewer.route,
        arguments = listOf(
            navArgument(Screen.ImageViewer.Parameters.TITLE) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(Screen.ImageViewer.Parameters.INITIAL_POSITION) {
                type = NavType.IntType
                defaultValue = 0
            },
            navArgument(Screen.ImageViewer.Parameters.IMAGE_URLS) {
                type = NavType.StringType
                nullable = true
            }
        ),
        enterTransition = {
            when (Screen.forRoute(initialState.destination.requireRoute())) {
                Screen.GameInfo -> HorizontalSliding.enter()
                else -> null
            }
        },
        exitTransition = { null },
        popEnterTransition = { null },
        popExitTransition = {
            when (Screen.forRoute(targetState.destination.requireRoute())) {
                Screen.GameInfo -> HorizontalSliding.popExit()
                else -> null
            }
        },
    ) {
        ImageViewer { route ->
            when (route) {
                is ImageViewerRoute.Back -> navController.popBackStack()
            }
        }
    }
}
