@file:JvmName("WindowUtils")

package ca.on.hojat.gamenews.shared.extensions

import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON

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
