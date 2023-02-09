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
import ca.on.hojat.gamenews.feature_category.CategoryScreenRoute
import ca.on.hojat.gamenews.feature_category.widgets.CategoryScreen
import ca.on.hojat.gamenews.feature_discovery.DiscoverScreenRoute
import ca.on.hojat.gamenews.feature_discovery.widgets.DiscoverScreen
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
        startDestination = START_DESTINATION.route,
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
        route = Destination.Discover.route,
        enterTransition = { null },
        exitTransition = {
            when (Destination.forRoute(targetState.destination.requireRoute())) {
                Destination.Search -> OvershootScaling.exit()
                Destination.Category,
                Destination.InfoPage -> HorizontalSliding.exit()
                else -> null
            }
        },
        popEnterTransition = {
            when (Destination.forRoute(initialState.destination.requireRoute())) {
                Destination.Search -> OvershootScaling.popEnter()
                Destination.Category,
                Destination.InfoPage -> HorizontalSliding.popEnter()
                else -> null
            }
        },
        popExitTransition = { null },
    ) {
        DiscoverScreen(modifier) { route ->
            when (route) {
                is DiscoverScreenRoute.Search -> {
                    navController.navigate(Destination.Search.route)
                }
                is DiscoverScreenRoute.Category -> {
                    navController.navigate(Destination.Category.createLink(route.category))
                }
                is DiscoverScreenRoute.Info -> {
                    navController.navigate(Destination.InfoPage.createLink(route.itemId))
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
        route = Destination.Likes.route,
        enterTransition = { null },
        exitTransition = {
            when (Destination.forRoute(targetState.destination.requireRoute())) {
                Destination.Search -> OvershootScaling.exit()
                Destination.InfoPage -> HorizontalSliding.exit()
                else -> null
            }
        },
        popEnterTransition = {
            when (Destination.forRoute(initialState.destination.requireRoute())) {
                Destination.Search -> OvershootScaling.popEnter()
                Destination.InfoPage -> HorizontalSliding.popEnter()
                else -> null
            }
        },
        popExitTransition = { null },
    ) {
        LikedGames(modifier) { route ->
            when (route) {
                is LikedGamesRoute.Search -> {
                    navController.navigate(Destination.Search.route)
                }
                is LikedGamesRoute.Info -> {
                    navController.navigate(Destination.InfoPage.createLink(route.gameId))
                }
            }
        }
    }
}

private fun NavGraphBuilder.newsScreen(modifier: Modifier) {
    composable(
        route = Destination.News.route,
    ) {
        GamingNews(modifier)
    }
}

private fun NavGraphBuilder.settingsScreen(modifier: Modifier) {
    composable(
        route = Destination.Settings.route,
    ) {
        Settings(modifier)
    }
}

private fun NavGraphBuilder.gamesSearchScreen(navController: NavHostController) {
    composable(
        route = Destination.Search.route,
        enterTransition = {
            when (Destination.forRoute(initialState.destination.requireRoute())) {
                Destination.News,
                Destination.Discover,
                Destination.Likes -> OvershootScaling.enter()
                else -> null
            }
        },
        exitTransition = {
            when (Destination.forRoute(targetState.destination.requireRoute())) {
                Destination.InfoPage -> HorizontalSliding.exit()
                else -> null
            }
        },
        popEnterTransition = {
            when (Destination.forRoute(initialState.destination.requireRoute())) {
                Destination.InfoPage -> HorizontalSliding.popEnter()
                else -> null
            }
        },
        popExitTransition = {
            when (Destination.forRoute(targetState.destination.requireRoute())) {
                Destination.News,
                Destination.Discover,
                Destination.Likes -> OvershootScaling.popExit()
                else -> null
            }
        },
    ) {
        GamesSearch { route ->
            when (route) {
                is GamesSearchRoute.Info -> {
                    navController.navigate(Destination.InfoPage.createLink(route.gameId))
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
        route = Destination.Category.route,
        arguments = listOf(
            navArgument(Destination.Category.Parameters.CATEGORY) { type = NavType.StringType },
        ),
        enterTransition = {
            when (Destination.forRoute(initialState.destination.requireRoute())) {
                Destination.Discover -> HorizontalSliding.enter()
                else -> null
            }
        },
        exitTransition = {
            when (Destination.forRoute(targetState.destination.requireRoute())) {
                Destination.InfoPage -> HorizontalSliding.exit()
                else -> null
            }
        },
        popEnterTransition = {
            when (Destination.forRoute(initialState.destination.requireRoute())) {
                Destination.InfoPage -> HorizontalSliding.popEnter()
                else -> null
            }
        },
        popExitTransition = {
            when (Destination.forRoute(targetState.destination.requireRoute())) {
                Destination.Discover -> HorizontalSliding.popExit()
                else -> null
            }
        },
    ) {
        CategoryScreen { route ->
            when (route) {
                is CategoryScreenRoute.Info -> {
                    navController.navigate(Destination.InfoPage.createLink(route.gameId))
                }
                is CategoryScreenRoute.Back -> {
                    navController.popBackStack()
                }
            }
        }
    }
}

private fun NavGraphBuilder.gameInfoScreen(navController: NavHostController) {
    composable(
        route = Destination.InfoPage.route,
        arguments = listOf(
            navArgument(Destination.InfoPage.Parameters.GAME_ID) { type = NavType.IntType },
        ),
        enterTransition = {
            when (Destination.forRoute(initialState.destination.requireRoute())) {
                Destination.Discover,
                Destination.Likes,
                Destination.Search,
                Destination.Category,
                Destination.InfoPage -> HorizontalSliding.enter()
                else -> null
            }
        },
        exitTransition = {
            when (Destination.forRoute(targetState.destination.requireRoute())) {
                Destination.ImageViewer,
                Destination.InfoPage -> HorizontalSliding.exit()
                else -> null
            }
        },
        popEnterTransition = {
            when (Destination.forRoute(initialState.destination.requireRoute())) {
                Destination.ImageViewer,
                Destination.InfoPage -> HorizontalSliding.popEnter()
                else -> null
            }
        },
        popExitTransition = {
            when (Destination.forRoute(targetState.destination.requireRoute())) {
                Destination.Discover,
                Destination.Likes,
                Destination.Search,
                Destination.Category,
                Destination.InfoPage -> HorizontalSliding.popExit()
                else -> null
            }
        },
    ) {
        GameInfo { route ->
            when (route) {
                is GameInfoRoute.ImageViewer -> {
                    navController.navigate(
                        Destination.ImageViewer.createLink(
                            title = route.title,
                            initialPosition = route.initialPosition,
                            imageUrls = route.imageUrls,
                        )
                    )
                }
                is GameInfoRoute.Info -> {
                    navController.navigate(Destination.InfoPage.createLink(route.gameId))
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
        route = Destination.ImageViewer.route,
        arguments = listOf(
            navArgument(Destination.ImageViewer.Parameters.TITLE) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(Destination.ImageViewer.Parameters.INITIAL_POSITION) {
                type = NavType.IntType
                defaultValue = 0
            },
            navArgument(Destination.ImageViewer.Parameters.IMAGE_URLS) {
                type = NavType.StringType
                nullable = true
            }
        ),
        enterTransition = {
            when (Destination.forRoute(initialState.destination.requireRoute())) {
                Destination.InfoPage -> HorizontalSliding.enter()
                else -> null
            }
        },
        exitTransition = { null },
        popEnterTransition = { null },
        popExitTransition = {
            when (Destination.forRoute(targetState.destination.requireRoute())) {
                Destination.InfoPage -> HorizontalSliding.popExit()
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
