package ca.on.hojat.gamehub.presentation

import androidx.navigation.NavDestination

fun NavDestination.requireRoute(): String {
    return checkNotNull(route) {
        "The route is not set for this destination."
    }
}
