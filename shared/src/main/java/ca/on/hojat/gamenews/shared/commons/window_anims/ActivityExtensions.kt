@file:JvmName("ActivityUtils")

package ca.on.hojat.gamenews.shared.commons.window_anims

import android.app.Activity

/**
 * Overrides the animations of the entering window.
 *
 * @param windowAnimations The animations to use for the entering window
 */
fun Activity.overrideEnterTransition(windowAnimations: WindowAnimations) {
    if (windowAnimations.id == WindowAnimations.DEFAULT_ANIMATIONS.id) {
        return
    }

    overridePendingTransition(
        windowAnimations.windowBEnterAnimation,
        windowAnimations.windowAExitAnimation
    )
}

/**
 * Overrides the animations of the exiting window.
 *
 * @param windowAnimations The animations to use for the exiting window
 */
fun Activity.overrideExitTransition(windowAnimations: WindowAnimations) {
    if (windowAnimations.id == WindowAnimations.DEFAULT_ANIMATIONS.id) {
        return
    }

    overridePendingTransition(
        windowAnimations.windowAEnterAnimation,
        windowAnimations.windowBExitAnimation
    )
}
