package com.paulrybitskyi.gamedge

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import ca.on.hojat.gamenews.shared.ui.HorizontalSliding
import ca.on.hojat.gamenews.shared.ui.OvershootScaling
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.paulrybitskyi.gamedge.feature.category.GamesCategoryRoute
import com.paulrybitskyi.gamedge.feature.category.widgets.GamesCategory
import com.paulrybitskyi.gamedge.feature.discovery.GamesDiscoveryRoute
import com.paulrybitskyi.gamedge.feature.discovery.widgets.GamesDiscovery
import com.paulrybitskyi.gamedge.feature.news.presentation.widgets.GamingNews
import com.paulrybitskyi.gamedge.feature.settings.presentation.Settings
import com.paulrybitskyi.gamedge.feature_image_viewer.ImageViewer
import com.paulrybitskyi.gamedge.feature_image_viewer.ImageViewerRoute
import com.paulrybitskyi.gamedge.feature_info.presentation.GameInfoRoute
import com.paulrybitskyi.gamedge.feature_info.presentation.widgets.main.GameInfo
import com.paulrybitskyi.gamedge.feature_likes.presentation.LikedGames
import com.paulrybitskyi.gamedge.feature_likes.presentation.LikedGamesRoute
import com.paulrybitskyi.gamedge.feature_search.presentation.GamesSearch
import com.paulrybitskyi.gamedge.feature_search.presentation.GamesSearchRoute

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
                Screen.GamesSearch -> OvershootScaling.exit()
                Screen.GamesCategory,
                Screen.GameInfo -> HorizontalSliding.exit()
                else -> null
            }
        },
        popEnterTransition = {
            when (Screen.forRoute(initialState.destination.requireRoute())) {
                Screen.GamesSearch -> OvershootScaling.popEnter()
                Screen.GamesCategory,
                Screen.GameInfo -> HorizontalSliding.popEnter()
                else -> null
            }
        },
        popExitTransition = { null },
    ) {
        GamesDiscovery(modifier) { route ->
            when (route) {
                is GamesDiscoveryRoute.Search -> {
                    navController.navigate(Screen.GamesSearch.route)
                }
                is GamesDiscoveryRoute.Category -> {
                    navController.navigate(Screen.GamesCategory.createLink(route.category))
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
                Screen.GamesSearch -> OvershootScaling.exit()
                Screen.GameInfo -> HorizontalSliding.exit()
                else -> null
            }
        },
        popEnterTransition = {
            when (Screen.forRoute(initialState.destination.requireRoute())) {
                Screen.GamesSearch -> OvershootScaling.popEnter()
                Screen.GameInfo -> HorizontalSliding.popEnter()
                else -> null
            }
        },
        popExitTransition = { null },
    ) {
        LikedGames(modifier) { route ->
            when (route) {
                is LikedGamesRoute.Search -> {
                    navController.navigate(Screen.GamesSearch.route)
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
        route = Screen.GamesSearch.route,
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
        route = Screen.GamesCategory.route,
        arguments = listOf(
            navArgument(Screen.GamesCategory.Parameters.CATEGORY) { type = NavType.StringType },
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
                Screen.GamesSearch,
                Screen.GamesCategory,
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
                Screen.GamesSearch,
                Screen.GamesCategory,
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
