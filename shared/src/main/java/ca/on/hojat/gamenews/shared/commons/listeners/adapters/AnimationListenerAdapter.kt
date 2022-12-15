package ca.on.hojat.gamenews.shared.commons.listeners.adapters

import android.view.animation.Animation

interface AnimationListenerAdapter : Animation.AnimationListener {
    override fun onAnimationStart(animation: Animation) {}
    override fun onAnimationRepeat(animation: Animation) {}
    override fun onAnimationEnd(animation: Animation) {}
}
