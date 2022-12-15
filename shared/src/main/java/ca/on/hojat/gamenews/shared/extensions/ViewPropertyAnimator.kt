@file:JvmName("ViewPropertyAnimatorUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.animation.Animator
import android.view.ViewPropertyAnimator

fun ViewPropertyAnimator.scale(value: Float): ViewPropertyAnimator {
    scaleX(value)
    scaleY(value)

    return this
}

fun ViewPropertyAnimator.scaleBy(value: Float): ViewPropertyAnimator {
    scaleXBy(value)
    scaleYBy(value)

    return this
}

inline fun ViewPropertyAnimator.setListener(
    crossinline onEnd: (animator: Animator) -> Unit = {},
    crossinline onStart: (animator: Animator) -> Unit = {},
    crossinline onCancel: (animator: Animator) -> Unit = {},
    crossinline onRepeat: (animator: Animator) -> Unit = {}
): ViewPropertyAnimator {
    return object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animator: Animator) = onRepeat(animator)
        override fun onAnimationEnd(animator: Animator) = onEnd(animator)
        override fun onAnimationCancel(animator: Animator) = onCancel(animator)
        override fun onAnimationStart(animator: Animator) = onStart(animator)
    }
        .let(::setListener)
}
