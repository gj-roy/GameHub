package ca.on.hojat.gamenews.shared.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController

fun NavController.containsDestination(@IdRes destinationId: Int): Boolean {
    return try {
        getBackStackEntry(destinationId)
        true
    } catch (ignore: Exception) {
        false
    }
}

fun NavController.containsAnyDestination(@IdRes destinationIds: List<Int>): Boolean {
    return destinationIds.any { containsDestination(it) }
}

fun NavController.getDestinationArgs(@IdRes destinationId: Int): Bundle? {
    return try {
        getBackStackEntry(destinationId).arguments
    } catch (ignore: Exception) {
        null
    }
}
