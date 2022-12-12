package ca.on.hojat.gamenews

import androidx.navigation.NavDestination

internal fun NavDestination.requireRoute(): String {
    return checkNotNull(route) {
        "The route is not set for this destination."
    }
}
