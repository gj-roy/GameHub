@file:JvmName("ViewPropertyAnimatorUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.view.ViewPropertyAnimator

fun ViewPropertyAnimator.scale(value: Float): ViewPropertyAnimator {
    scaleX(value)
    scaleY(value)

    return this
}
