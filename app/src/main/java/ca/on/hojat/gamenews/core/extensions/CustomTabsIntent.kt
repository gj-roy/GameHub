package ca.on.hojat.gamenews.core.extensions

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import ca.on.hojat.gamenews.core.animations.WindowAnimations

fun CustomTabsIntent.Builder.setAnimations(
    context: Context, windowAnimations: WindowAnimations
): CustomTabsIntent.Builder {
    setStartAnimations(
        context, windowAnimations.windowBEnterAnimation, windowAnimations.windowAExitAnimation
    )
    setExitAnimations(
        context, windowAnimations.windowAEnterAnimation, windowAnimations.windowBExitAnimation
    )

    return this
}
