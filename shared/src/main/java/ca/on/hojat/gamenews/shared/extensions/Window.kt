@file:JvmName("WindowUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND
import android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON

var Window.isBackgroundDimmingEnabled: Boolean
    set(value) {
        if (value) {
            addFlags(FLAG_DIM_BEHIND)
        } else {
            clearFlags(FLAG_DIM_BEHIND)
        }
    }
    get() = ((attributes.flags and FLAG_DIM_BEHIND) == FLAG_DIM_BEHIND)

fun Window.makeScreenAlwaysAwake() {
    addFlags(FLAG_KEEP_SCREEN_ON)
}

fun Window.makeScreenSleepable() {
    clearFlags(FLAG_KEEP_SCREEN_ON)
}

fun Window.setScreenAlwaysAwake(isScreenAlwaysAwake: Boolean) {
    if (isScreenAlwaysAwake) {
        makeScreenAlwaysAwake()
    } else {
        makeScreenSleepable()
    }
}
