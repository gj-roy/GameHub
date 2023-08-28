package ca.on.hojat.gamehub.core.extensions

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import ca.on.hojat.gamehub.presentation.WindowAnimations

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
